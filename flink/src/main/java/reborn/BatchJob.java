/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reborn;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * Skeleton for a Flink Batch Job.
 *
 * <p>For a tutorial how to write a Flink batch application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution,
 * change the main class in the POM.xml file to this class (simply search for 'mainClass')
 * and run 'mvn clean package' on the command line.
 */
public class BatchJob {

	public static void main(String[] args) throws Exception {
		// set up the batch execution environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// execute program
		DataSet<Integer> dataSet = env.fromElements(1, 2, 2, 4, 3, 6, 6, 87, 10);
		DataSet<Tuple2<Integer, Integer>> count = wordCount2(dataSet);
		count.print();

		//env.execute("Flink Batch Java API Skeleton");
	}


	/**
	 * flink和Lambda使用时 对于使用泛型的场景 需要生命具体的类型
	 * https://ci.apache.org/projects/flink/flink-docs-release-1.7/dev/java_lambdas.html
	 * */
	private static DataSet<Tuple2<Integer, Integer>> wordCount1(DataSet<Integer> dataSet) {
		return dataSet.map(new MapFunction<Integer, Tuple2<Integer, Integer>>() {
			@Override
			public Tuple2<Integer, Integer> map(Integer i) throws Exception {
				return Tuple2.of(i, 1);
			}
		}).groupBy(0).sum(1);
	}

	private static DataSet<Tuple2<Integer, Integer>> wordCount2(DataSet<Integer> dataSet) {
		return dataSet.map(i -> Tuple2.of(i, 1))
				.returns(Types.TUPLE(Types.INT, Types.INT))
				.groupBy(0)
				.sum(1);
	}
}
