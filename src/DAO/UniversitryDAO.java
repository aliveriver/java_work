package DAO;

import java.sql.*;

public class UniversitryDAO {

    public void Create(model.University u,Connection conn) throws Exception{
        String sql = "INSERT INTO universities (university_id,name,location) VALUES(?,?,?)";
        PreparedStatement PStat = conn.prepareStatement(sql);
        PStat.setInt(1,u.getUniversity_id());
        PStat.setString(2,u.getName());
        PStat.setString(3,u.getLocation());
        int row = PStat.executeUpdate();
        if(row>0) {
            System.out.println("success insert");
        }
        else{
            System.out.println("fail to insert");
        }
        PStat.close();
        return;
    }

    public void Upadte(model.University u,Connection conn) {

    }

    public void SelectById(int id,Connection conn) {

    }

    public void Delete(model.University u,Connection conn) {

    }
}
