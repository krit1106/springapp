package java.spring.dao;

import java.spring.model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ContactDAOImpl implements ContactDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void save(Contact contact) {
		// TODO Auto-generated method stub
		System.out.println("implementing");
		
		if(contact.getId() > 0){
			//update
			String sql = "UPDATE contact SET name=?, email=?, address=?, telephone=? WHERE contact_id=?";
			
			Object[] params = {contact.getName(), contact.getEmail(), contact.getAddress(), contact.getTelephone(), contact.getId()};
			
			jdbcTemplate.update(sql, params);
		} else{
			//insert
			
			String sql = "INSERT INTO contact (name, email, address, telephone) VALUES(?, ?, ?, ?)";
			
			Object[] params = {contact.getName(), contact.getEmail(), contact.getAddress(), contact.getTelephone(), contact.getId()};
			
			jdbcTemplate.update(sql, params);
		}
	}

	public void delete(int contactId) {
		// TODO Auto-generated method stub
		System.out.println("implementing");
		
		String sql = "DELETE from contact where contact_id=?";
		Object[] cid = {contactId};
		jdbcTemplate.update(sql, cid);
	}

	public Contact get(int contactId) {
		// TODO Auto-generated method stub
		System.out.println("implementing");
		
		String sql = "Select * from contact where contact_id =" + contactId;
		return (Contact) jdbcTemplate.query(sql, new ResultSetExtractor() {
			
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				if(rs.next()){
					Contact c = new Contact();
					c.setId(rs.getInt("contact_id"));
					c.setName(rs.getString("name"));
					c.setEmail(rs.getString("email"));
					c.setAddress(rs.getString("address"));
					c.setTelephone(rs.getString("telephone"));
					return c;
				}
				
				return 0;
			}
		});
		
	}

	public List<Contact> list() {
		// TODO Auto-generated method stub
		System.out.println("implementing");
		
		String sql = "Select * from contact";
		List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper() {
			
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Contact c = new Contact();
				c.setId(rs.getInt("contact_id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setAddress(rs.getString("address"));
				c.setTelephone(rs.getString("telephone"));
				return c;
			}
		});
		return listContact;
	}

}
