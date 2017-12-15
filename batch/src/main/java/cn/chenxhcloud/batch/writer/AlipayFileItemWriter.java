package cn.chenxhcloud.batch.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import cn.chenxhcloud.batch.dto.MyPayTranDO;

/**
 * 
 * @author Administrator
 *
 */
@Component
public class AlipayFileItemWriter {
	@Value("${spring.batch.file-path.dist}")
	private String fileDistPath;
	
	public FlatFileItemWriter<MyPayTranDO> getAlipayItemWriter() {
        FlatFileItemWriter<MyPayTranDO> txtItemWriter = new FlatFileItemWriter<MyPayTranDO>();
        txtItemWriter.setAppendAllowed(true);
        txtItemWriter.setShouldDeleteIfExists(true);
        txtItemWriter.setEncoding("UTF-8");
        txtItemWriter.setResource(new FileSystemResource(fileDistPath+"result-data.txt"));
        txtItemWriter.setLineAggregator(new DelimitedLineAggregator<MyPayTranDO>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<MyPayTranDO>() {{
                setNames(new String[]{"tranId", "channel", "tranType", "counterparty", "goods", "amount", "isDebitCredit", "state", "tranDate", "merId" });
            }});
        }});
        return txtItemWriter;
    }
}
