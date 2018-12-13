package es.redmic.jts4jackson.linestring;

import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONException;
import org.junit.Test;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import es.redmic.jts4jackson.module.JTSModule;

public class LineStringSerializerDeserializerTest {

	private String dataFile = "/data/lineString.json";

	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void sourceAndExpectedJson_AreEqual_IfDeserializeAndSerializeLineString()
			throws JsonParseException, JsonMappingException, IOException, JSONException {

		mapper.registerModule(new JTSModule());

		LineString lineString = mapper.readValue(getClass().getResource(dataFile).openStream(), LineString.class);

		String source = new String(Files.readAllBytes(new ClassPathResource(dataFile).getFile().toPath()));

		String expected = mapper.writeValueAsString(lineString);

		JSONAssert.assertEquals(expected, source, false);
	}

	@Test(expected = InvalidDefinitionException.class)
	public void deserialize_ThrowExeption_IfModuleIsNotRegistry()
			throws JsonParseException, JsonMappingException, IOException, JSONException {

		mapper.readValue(getClass().getResource(dataFile).openStream(), LineString.class);
	}

	@Test(expected = JsonMappingException.class)
	public void serialize_ThrowExeption_IfModuleIsNotRegistry()
			throws JsonParseException, JsonMappingException, IOException, JSONException {

		mapper.writeValueAsString(new GeometryFactory().createLineString());
	}
}