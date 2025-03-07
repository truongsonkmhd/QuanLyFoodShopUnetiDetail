package BUS;

import DAO.PBSanPhamDao;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author Pham Truong Son
 */
public class PhienBanSanPhamBUS {
    private PBSanPhamDao pbsp = new PBSanPhamDao();
    
    public PhienBanSanPhamBUS() {
    }
    
    public ArrayList<PhienBanSanPhamDTO> getAll(int masp) {
        return pbsp.selectAllpb(Integer.toString(masp));
    }
    
    public PhienBanSanPhamDTO getByMaPhienBan(int mapb) {
        return pbsp.selectById(mapb);
    }
    public int getIndexByMaPhienBan(ArrayList<PhienBanSanPhamDTO> list, int mapb) {
        int i = 0;
        int vitri = -1;
        while(i < list.size() && vitri == -1) {
            if(list.get(i).getMaphienbansp() == mapb) {
                vitri = i;
            } else i++;
        }
        return vitri;
    }
    
    public void getStringListImei() {
    }
    
    public static boolean checkDuplicate(ArrayList<PhienBanSanPhamDTO> listch, PhienBanSanPhamDTO ch) {
        boolean check = false;
        int i = 0;
        while(i < listch.size() && check == false) {
            if(listch.get(i).equals(ch)) check = true;
            else i++;
        }
        return check;
    }
    
    public Boolean add(ArrayList<PhienBanSanPhamDTO> listch) {
        boolean check = pbsp.insert(listch) != 0;
        return check;
    } 
    
    public int getSoluong(int maphienban){
        return pbsp.selectById(maphienban).getSoluongton();
    }
    
    public boolean checkImeiExists(ArrayList<ChiTietSanPhamDTO> arr){
        return pbsp.checkImeiExists(arr);
    }
}

