package java.spring.dao;
import java.spring.model.Contact;
import java.util.List;

public interface ContactDAO {
	public void save(Contact contact); //save or update method
	
	public void delete(int contactId); //delete records method
	
	public Contact get(int contactId); //fetch data method
	
	public List<Contact> list();
	
}
