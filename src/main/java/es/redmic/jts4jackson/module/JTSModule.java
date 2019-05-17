package es.redmic.jts4jackson.module;

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

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;

import es.redmic.jts4jackson.linestring.LineStringDeserializer;
import es.redmic.jts4jackson.linestring.LineStringSerializer;
import es.redmic.jts4jackson.point.PointDeserializer;
import es.redmic.jts4jackson.point.PointSerializer;
import es.redmic.jts4jackson.polygon.PolygonDeserializer;
import es.redmic.jts4jackson.polygon.PolygonSerializer;

public class JTSModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JTSModule() {

		super(PackageVersion.VERSION);

		// deserializers
		addDeserializer(Point.class, new PointDeserializer());
		addDeserializer(LineString.class, new LineStringDeserializer());
		addDeserializer(Polygon.class, new PolygonDeserializer());

		// serializers:
		addSerializer(Point.class, new PointSerializer());
		addSerializer(LineString.class, new LineStringSerializer());
		addSerializer(Polygon.class, new PolygonSerializer());
	}

	@Override
	public String getModuleName() {
		return getClass().getSimpleName();
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return this == o;
	}
}
