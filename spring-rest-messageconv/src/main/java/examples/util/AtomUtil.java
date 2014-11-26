package examples.util;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import examples.messageconv.domain.Organization;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtomUtil {

	public static Map<String, Object> marshallerProperties;
	
	static {
		marshallerProperties = new HashMap<String, Object>();
		marshallerProperties.put("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
	}
	
	public static Feed employeeFeed(List<Organization> organizations, Jaxb2Marshaller marshaller) {
		Feed feed = new Feed();
		feed.setFeedType("atom_1.0");
		feed.setTitle("Employee Atom Feed");
		
		List<Entry> entries = new ArrayList<Entry>();
		for(Organization organization : organizations) {
			StreamResult result = new StreamResult(new ByteArrayOutputStream());
			
			marshaller.setMarshallerProperties(marshallerProperties);
			marshaller.marshal(organization, result);
			String xml = result.getOutputStream().toString();
			
			Entry entry = new Entry();
			entry.setId(Long.valueOf(organization.getId()).toString());
			entry.setTitle(organization.getName());
			Content content = new Content();
			content.setType(Content.XML);
			content.setValue(xml);
			
			List<Content> contents = new ArrayList<Content>();
			contents.add(content);
			entry.setContents(contents);
			
			entries.add(entry);
		}
		feed.setEntries(entries);
		return feed;
	}
}
