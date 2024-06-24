package xuwei.tech.batch.batchAPI;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.util.EnvironmentInformation;

public class BatchDemoCounter {
	
	public static void main(String[] args) throws Exception {
		ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
		DataSource<String> fromElements = executionEnvironment.fromElements("a","c","v","t");
		// aa
		// bb

		MapOperator<String, String> map = fromElements.map(new RichMapFunction<String, String>() {
			IntCounter aa=new IntCounter();
			@Override
			public void open(Configuration parameters) throws Exception {
				 super.open(parameters);
				getRuntimeContext().addAccumulator("counter", aa);
			}
			
			@Override
			public String map(String value) throws Exception {
				aa.add(1);
				return value;
			}
		});
		map.writeAsText("C:\\bigdata\\resultww");
		JobExecutionResult execute = executionEnvironment.execute("aa");
		int accumulatorResult = execute.getAccumulatorResult("counter");
		System.out.println(accumulatorResult);
		
	};
}
