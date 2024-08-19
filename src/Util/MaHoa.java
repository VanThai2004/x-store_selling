/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author TienBB
 */
public class MaHoa {

    public static String MaHoa(String matKhau) {
        try {
            // Tạo salt ngẫu nhiên
            byte[] salt = generateSalt();
            String saltString = Base64.encodeBase64String(salt);

            // Kết hợp mật khẩu và salt
            String matKhauVoiSalt = matKhau + saltString;

            // Sử dụng SHA-256 để băm mật khẩu
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(matKhauVoiSalt.getBytes());

            // Mã hóa kết quả băm bằng cơ sở 64
            return Base64.encodeBase64String(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức để tạo salt ngẫu nhiên để thêm vào mật khẩu mã hóa
    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
    }

    public static void main(String[] args) {
        String mk = "123";
        String maHoaMK = MaHoa(mk);
        System.out.println(maHoaMK);
    }
}
