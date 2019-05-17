package es.redmic.jts4jackson.polygon;

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

import org.locationtech.jts.geom.Polygon;
import org.locationtech.spatial4j.io.jackson.GeometryAsGeoJSONSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PolygonSerializer extends JsonSerializer<Polygon> {

	final GeometryAsGeoJSONSerializer ser;

	public PolygonSerializer() {
		ser = new GeometryAsGeoJSONSerializer();
	}

	@Override
	public void serialize(Polygon value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		ser.serialize(value, gen, serializers);
	}
}
