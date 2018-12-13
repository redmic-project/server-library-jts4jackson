package es.redmic.jts4jackson.module;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;

import es.redmic.jts4jackson.point.PointDeserializer;
import es.redmic.jts4jackson.point.PointSerializer;

public class JTSModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JTSModule() {

		super(PackageVersion.VERSION);

		// deserializers
		addDeserializer(Point.class, new PointDeserializer());

		// serializers:
		addSerializer(Point.class, new PointSerializer());
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