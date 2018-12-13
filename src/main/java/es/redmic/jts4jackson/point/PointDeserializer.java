package es.redmic.jts4jackson.point;

import java.io.IOException;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.spatial4j.io.jackson.GeometryDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PointDeserializer extends JsonDeserializer<Point> {

	final GeometryDeserializer dser;

	public PointDeserializer() {
		dser = new GeometryDeserializer();
	}

	@Override
	public Point deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Geometry geometry = dser.deserialize(jp, ctxt);
		if (geometry != null) {
			return (Point) geometry;
		}
		return null;
	}
}
