package es.redmic.jts4jackson.linestring;

import java.io.IOException;

import org.locationtech.jts.geom.LineString;
import org.locationtech.spatial4j.io.jackson.GeometryAsGeoJSONSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LineStringSerializer extends JsonSerializer<LineString> {

	final GeometryAsGeoJSONSerializer ser;

	public LineStringSerializer() {
		ser = new GeometryAsGeoJSONSerializer();
	}

	@Override
	public void serialize(LineString value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		ser.serialize(value, gen, serializers);
	}
}
