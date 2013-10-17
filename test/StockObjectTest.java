import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.k2i.adnjoy.business.File;
import fr.k2i.adnjoy.business.object.StockObject;
import fr.k2i.adnjoy.dao.FileDao;
import fr.k2i.adnjoy.dao.StockObjectDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-*.xml")  
public class StockObjectTest {
	
    private StockObjectDao stockObjectDao;
    private FileDao fileDao;
    
    @Resource
    public void setStockObjectDao(StockObjectDao stockObjectDao) {
		this.stockObjectDao = stockObjectDao;
	}
    @Resource
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
    }
    
	private String directory = "/home/dimitri/adnjoy/images/";
	private String type = "logo/";


	@Transactional
	@Test
	public void testadd() throws Exception {
//		List<StockObject> byBrandId = stockObjectDao.getByBrandId(null);
//		
//		for (StockObject stockObject : byBrandId) {
//			stockObjectDao.delete(stockObject);
//		}
		
		for (int i = 50; i < 10000; i+=50) {

			File f = new File();
			f.setContentType("image/png");
			String filename = "logo.png";
//			File file = new File(fileBean, directory+videoDirectory,"ad");
			String dlUrl = type+i+"/"+filename;
			String file= directory+filename;
			f.setDlUrl(dlUrl);
			f.setFilename(filename);
			f.setFileSize(9811L);
			f.setFile(file);
			fileDao.save(f);
			
			StockObject s = new StockObject();
			s.setAvailable(100L);
			s.setBrand(null);
			s.setDescription("Bon d'achat de "+i+"euros valable chez tous nos partenaires");
			s.setName("Bon d'achat de "+ i +" euros");
			s.setDlFile(f);
			s.setValue(new Double(i));
			stockObjectDao.save(s);
		}
		

	}



}
