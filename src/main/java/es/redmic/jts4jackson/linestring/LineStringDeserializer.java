package es.redmic.jts4jackson.linestring;

import java.io.IOException;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.spatial4j.io.jackson.GeometryDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LineStringDeserializer extends JsonDeserializer<LineString> {

	final GeometryDeserializer dser;

	public LineStringDeserializer() {
		dser = new GeometryDeserializer();
	}

	@Override
	public LineString deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		Geometry geometry = dser.deserialize(jp, ctxt);
		if (geometry != null) {
			return (LineString) geometry;
		}
		return null;
	}
}
