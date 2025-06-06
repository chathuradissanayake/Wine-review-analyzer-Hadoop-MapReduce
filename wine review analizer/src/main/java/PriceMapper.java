import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class PriceMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private boolean isHeader = true;
    private Text variety = new Text();
    private DoubleWritable price = new DoubleWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] columns = line.split(",", -1); 
        
        if (isHeader) {
            isHeader = false;
            return;
        }

        
        if (columns.length < 5) return;

        String priceStr = columns[3].trim();    
        String varietyStr = columns[4].trim(); 

        
        if (priceStr.isEmpty() || varietyStr.isEmpty()) return;

        try {
            double priceVal = Double.parseDouble(priceStr);
            variety.set(varietyStr);
            price.set(priceVal);
            context.write(variety, price);
        } catch (NumberFormatException e) {
            
        }
    }
}
