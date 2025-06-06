import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class AveragePriceReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    private final DoubleWritable averagePrice = new DoubleWritable();

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double sum = 0;
        int count = 0;

        for (DoubleWritable value : values) {
            sum += value.get();
            count++;
        }

        if (count > 0) {
            averagePrice.set(sum / count);
            context.write(key, averagePrice);
        }
    }
}
