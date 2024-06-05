package cdm.observable.asset;

import cdm.observable.asset.Curve;
import cdm.observable.asset.InformationSource;
import cdm.observable.asset.ObservationSource;
import cdm.observable.asset.ObservationSource.ObservationSourceBuilder;
import cdm.observable.asset.ObservationSource.ObservationSourceBuilderImpl;
import cdm.observable.asset.ObservationSource.ObservationSourceImpl;
import cdm.observable.asset.meta.ObservationSourceMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * The observation source can be composed of an curve and/or and information source.
 * @version ${project.version}
 */
@RosettaDataType(value="ObservationSource", builder=ObservationSource.ObservationSourceBuilderImpl.class, version="${project.version}")
public interface ObservationSource extends RosettaModelObject {

	ObservationSourceMeta metaData = new ObservationSourceMeta();

	/*********************** Getter Methods  ***********************/
	Curve getCurve();
	InformationSource getInformationSource();

	/*********************** Build Methods  ***********************/
	ObservationSource build();
	
	ObservationSource.ObservationSourceBuilder toBuilder();
	
	static ObservationSource.ObservationSourceBuilder builder() {
		return new ObservationSource.ObservationSourceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ObservationSource> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ObservationSource> getType() {
		return ObservationSource.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("curve"), processor, Curve.class, getCurve());
		processRosetta(path.newSubPath("informationSource"), processor, InformationSource.class, getInformationSource());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationSourceBuilder extends ObservationSource, RosettaModelObjectBuilder {
		Curve.CurveBuilder getOrCreateCurve();
		Curve.CurveBuilder getCurve();
		InformationSource.InformationSourceBuilder getOrCreateInformationSource();
		InformationSource.InformationSourceBuilder getInformationSource();
		ObservationSource.ObservationSourceBuilder setCurve(Curve curve);
		ObservationSource.ObservationSourceBuilder setInformationSource(InformationSource informationSource);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("curve"), processor, Curve.CurveBuilder.class, getCurve());
			processRosetta(path.newSubPath("informationSource"), processor, InformationSource.InformationSourceBuilder.class, getInformationSource());
		}
		

		ObservationSource.ObservationSourceBuilder prune();
	}

	/*********************** Immutable Implementation of ObservationSource  ***********************/
	class ObservationSourceImpl implements ObservationSource {
		private final Curve curve;
		private final InformationSource informationSource;
		
		protected ObservationSourceImpl(ObservationSource.ObservationSourceBuilder builder) {
			this.curve = ofNullable(builder.getCurve()).map(f->f.build()).orElse(null);
			this.informationSource = ofNullable(builder.getInformationSource()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("curve")
		public Curve getCurve() {
			return curve;
		}
		
		@Override
		@RosettaAttribute("informationSource")
		public InformationSource getInformationSource() {
			return informationSource;
		}
		
		@Override
		public ObservationSource build() {
			return this;
		}
		
		@Override
		public ObservationSource.ObservationSourceBuilder toBuilder() {
			ObservationSource.ObservationSourceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ObservationSource.ObservationSourceBuilder builder) {
			ofNullable(getCurve()).ifPresent(builder::setCurve);
			ofNullable(getInformationSource()).ifPresent(builder::setInformationSource);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationSource _that = getType().cast(o);
		
			if (!Objects.equals(curve, _that.getCurve())) return false;
			if (!Objects.equals(informationSource, _that.getInformationSource())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (curve != null ? curve.hashCode() : 0);
			_result = 31 * _result + (informationSource != null ? informationSource.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationSource {" +
				"curve=" + this.curve + ", " +
				"informationSource=" + this.informationSource +
			'}';
		}
	}

	/*********************** Builder Implementation of ObservationSource  ***********************/
	class ObservationSourceBuilderImpl implements ObservationSource.ObservationSourceBuilder {
	
		protected Curve.CurveBuilder curve;
		protected InformationSource.InformationSourceBuilder informationSource;
	
		public ObservationSourceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("curve")
		public Curve.CurveBuilder getCurve() {
			return curve;
		}
		
		@Override
		public Curve.CurveBuilder getOrCreateCurve() {
			Curve.CurveBuilder result;
			if (curve!=null) {
				result = curve;
			}
			else {
				result = curve = Curve.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("informationSource")
		public InformationSource.InformationSourceBuilder getInformationSource() {
			return informationSource;
		}
		
		@Override
		public InformationSource.InformationSourceBuilder getOrCreateInformationSource() {
			InformationSource.InformationSourceBuilder result;
			if (informationSource!=null) {
				result = informationSource;
			}
			else {
				result = informationSource = InformationSource.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("curve")
		public ObservationSource.ObservationSourceBuilder setCurve(Curve curve) {
			this.curve = curve==null?null:curve.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("informationSource")
		public ObservationSource.ObservationSourceBuilder setInformationSource(InformationSource informationSource) {
			this.informationSource = informationSource==null?null:informationSource.toBuilder();
			return this;
		}
		
		@Override
		public ObservationSource build() {
			return new ObservationSource.ObservationSourceImpl(this);
		}
		
		@Override
		public ObservationSource.ObservationSourceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationSource.ObservationSourceBuilder prune() {
			if (curve!=null && !curve.prune().hasData()) curve = null;
			if (informationSource!=null && !informationSource.prune().hasData()) informationSource = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCurve()!=null && getCurve().hasData()) return true;
			if (getInformationSource()!=null && getInformationSource().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationSource.ObservationSourceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ObservationSource.ObservationSourceBuilder o = (ObservationSource.ObservationSourceBuilder) other;
			
			merger.mergeRosetta(getCurve(), o.getCurve(), this::setCurve);
			merger.mergeRosetta(getInformationSource(), o.getInformationSource(), this::setInformationSource);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationSource _that = getType().cast(o);
		
			if (!Objects.equals(curve, _that.getCurve())) return false;
			if (!Objects.equals(informationSource, _that.getInformationSource())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (curve != null ? curve.hashCode() : 0);
			_result = 31 * _result + (informationSource != null ? informationSource.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationSourceBuilder {" +
				"curve=" + this.curve + ", " +
				"informationSource=" + this.informationSource +
			'}';
		}
	}
}
