package cn.chenxhcloud.batch.reader;


import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cn.chenxhcloud.batch.dto.AlipayTranDO;


/**
 * 
 * @author Administrator
 *
 */
@Component
public class AlipayFileItemReader {
	
	private FlatFileItemReader<AlipayTranDO> reader;
	
	@Value("${spring.batch.file-path.src}")
	private String fileSrcPath;
	
    public FlatFileItemReader<AlipayTranDO> getAlipayFileItemReader() {
        reader = new FlatFileItemReader<AlipayTranDO>();
        reader.setLineMapper(new DefaultLineMapper<AlipayTranDO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "tranId", "channel", "tranType", "counterparty", "goods", "amount", "isDebitCredit", "state" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<AlipayTranDO>() {{
                setTargetType(AlipayTranDO.class);
            }});
        }});
        reader.setLinesToSkip(5);
        return reader;
    }

    public MultiResourceItemReader<AlipayTranDO> getMultiAliReader() {
        //TODO: 获取所有当天待加载的支付宝账单,
        // 这里只是简单的放了两个csv账单文件，
        // 实际处理过程中，肯定是从数据库或者接口获取需要加载的账单文件路径
        MultiResourceItemReader<AlipayTranDO> reader = new MultiResourceItemReader<AlipayTranDO>();
        Resource[] files = new Resource[]{new FileSystemResource(fileSrcPath+"208012345_20141030.csv"),
                new FileSystemResource(fileSrcPath+"208054321_20141030.csv")};
        reader.setResources(files);
        reader.setDelegate(this.getAlipayFileItemReader());
        return reader;
    }

}
