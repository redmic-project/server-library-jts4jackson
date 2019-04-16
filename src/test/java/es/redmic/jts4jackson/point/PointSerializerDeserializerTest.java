package es.redmic.jts4jackson.point;

/*-
 * #%L
 * jts4jackson
 * %%
 * Copyright (C) 2019 REDMIC Project / Server
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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

		String source = new String(Files.readAllBytes(new ClassPathResource(dataFile).getFile().toPath()));

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
