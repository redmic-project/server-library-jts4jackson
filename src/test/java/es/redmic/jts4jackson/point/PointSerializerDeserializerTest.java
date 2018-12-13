package es.redmic.jts4jackson.point;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONException;
import org.junit.Test;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import es.redmic.jts4jackson.module.JTSModule;

public class PointSerializerDeserializerTest {

	private String dataFile = "/data/point.json";

	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void sourceAndExpectedJson_AreEqual_IfDeserializeAndSerializePoint()
			throws JsonParseException, JsonMappingException, IOException, JSONException {

		mapper.registerModule(new JTSModule());

		Point point = mapper.readValue(getClass().getResource(dataFile).openStream(), Point.class);

		File resource = new ClassPathResource(dataFile).getFile();

		String source = new String(Files.readAllBytes(resource.toPath()));

		String expected = mapper.writeValueAsString(point);

		JSONAssert.assertEquals(expected, source, false);
	}

	@Test(expected = InvalidDefinitionException.class)
	public void deserialize_ThrowExeption_IfModuleIsNotRegistry()
			throws JsonParseException, JsonMappingException, IOException, JSONException {

		mapper.readValue(getClass().getResource(dataFile).openStream(), Point.class);
	}

	@Test(expected = JsonMappingException.class)
	public void serialize_ThrowExeption_IfModuleIsNotRegistry()
			throws JsonParseException, JsonMappingException, IOException, JSONException {

		mapper.writeValueAsString(new GeometryFactory().createPoint());
	}
}
