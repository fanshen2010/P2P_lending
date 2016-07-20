package cn.com.p2p.cms;

import java.io.Serializable;
import java.util.List;

import cn.com.p2p.domain.cms.entity.Advertisement;
import cn.com.p2p.domain.cms.entity.Advertising;
import cn.com.p2p.domain.system.entity.UploadFile;


/**
 * <p>广告管理数据传输对象</p>
 * @author
 */
public class ADDto implements Serializable{

    private static final long serialVersionUID = 8365833842809657649L;

    /** 广告栏目实体 */
    private Advertising advertising;
    
    /** 广告栏目实体集合 */
    private List<Advertising> advertisings;
    
    /** 广告内容实体 */
    private Advertisement advertisement;
    
    /** 用于保存顺序 */
    private List<Advertisement> advertisements;
    
    /** 广告内容实体集合 ，仅仅供界面顺序显示用，没有实际意义*/
    private List<List<Advertisement>> adLists;
    
    /** 文件上传实体 */
    private UploadFile uploadFile;
    
/* =========================================  getter、setter 方法    ========================================== */
    public Advertising getAdvertising() {
        return advertising;
    }

    public void setAdvertising(Advertising advertising) {
        this.advertising = advertising;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public List<List<Advertisement>> getAdLists() {
        return adLists;
    }

    public void setAdLists(List<List<Advertisement>> adLists) {
        this.adLists = adLists;
    }

    public List<Advertising> getAdvertisings() {
        return advertisings;
    }

    public void setAdvertisings(List<Advertising> advertisings) {
        this.advertisings = advertisings;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public UploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

}
