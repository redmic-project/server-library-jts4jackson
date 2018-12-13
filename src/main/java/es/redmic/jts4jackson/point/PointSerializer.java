package es.redmic.jts4jackson.point;

import java.io.IOException;

import org.locationtech.jts.geom.Point;
import org.locationtech.spatial4j.io.jackson.GeometryAsGeoJSONSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PointSerializer extends JsonSerializer<Point> {

	final GeometryAsGeoJSONSerializer ser;

	public PointSerializer() {
		ser = new GeometryAsGeoJSONSerializer();
	}

	@Override
	public void serialize(Point value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		ser.serialize(value, gen, serializers);
	}
}
