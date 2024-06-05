package cdm.regulation;

import cdm.regulation.Sngl;
import cdm.regulation.SwpOut;
import cdm.regulation.SwpOut.SwpOutBuilder;
import cdm.regulation.SwpOut.SwpOutBuilderImpl;
import cdm.regulation.SwpOut.SwpOutImpl;
import cdm.regulation.meta.SwpOutMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="SwpOut", builder=SwpOut.SwpOutBuilderImpl.class, version="${project.version}")
public interface SwpOut extends RosettaModelObject {

	SwpOutMeta metaData = new SwpOutMeta();

	/*********************** Getter Methods  ***********************/
	Sngl getSngl();

	/*********************** Build Methods  ***********************/
	SwpOut build();
	
	SwpOut.SwpOutBuilder toBuilder();
	
	static SwpOut.SwpOutBuilder builder() {
		return new SwpOut.SwpOutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SwpOut> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SwpOut> getType() {
		return SwpOut.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("sngl"), processor, Sngl.class, getSngl());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SwpOutBuilder extends SwpOut, RosettaModelObjectBuilder {
		Sngl.SnglBuilder getOrCreateSngl();
		Sngl.SnglBuilder getSngl();
		SwpOut.SwpOutBuilder setSngl(Sngl sngl);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("sngl"), processor, Sngl.SnglBuilder.class, getSngl());
		}
		

		SwpOut.SwpOutBuilder prune();
	}

	/*********************** Immutable Implementation of SwpOut  ***********************/
	class SwpOutImpl implements SwpOut {
		private final Sngl sngl;
		
		protected SwpOutImpl(SwpOut.SwpOutBuilder builder) {
			this.sngl = ofNullable(builder.getSngl()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("sngl")
		public Sngl getSngl() {
			return sngl;
		}
		
		@Override
		public SwpOut build() {
			return this;
		}
		
		@Override
		public SwpOut.SwpOutBuilder toBuilder() {
			SwpOut.SwpOutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SwpOut.SwpOutBuilder builder) {
			ofNullable(getSngl()).ifPresent(builder::setSngl);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SwpOut _that = getType().cast(o);
		
			if (!Objects.equals(sngl, _that.getSngl())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sngl != null ? sngl.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SwpOut {" +
				"sngl=" + this.sngl +
			'}';
		}
	}

	/*********************** Builder Implementation of SwpOut  ***********************/
	class SwpOutBuilderImpl implements SwpOut.SwpOutBuilder {
	
		protected Sngl.SnglBuilder sngl;
	
		public SwpOutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("sngl")
		public Sngl.SnglBuilder getSngl() {
			return sngl;
		}
		
		@Override
		public Sngl.SnglBuilder getOrCreateSngl() {
			Sngl.SnglBuilder result;
			if (sngl!=null) {
				result = sngl;
			}
			else {
				result = sngl = Sngl.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("sngl")
		public SwpOut.SwpOutBuilder setSngl(Sngl sngl) {
			this.sngl = sngl==null?null:sngl.toBuilder();
			return this;
		}
		
		@Override
		public SwpOut build() {
			return new SwpOut.SwpOutImpl(this);
		}
		
		@Override
		public SwpOut.SwpOutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SwpOut.SwpOutBuilder prune() {
			if (sngl!=null && !sngl.prune().hasData()) sngl = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSngl()!=null && getSngl().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SwpOut.SwpOutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SwpOut.SwpOutBuilder o = (SwpOut.SwpOutBuilder) other;
			
			merger.mergeRosetta(getSngl(), o.getSngl(), this::setSngl);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SwpOut _that = getType().cast(o);
		
			if (!Objects.equals(sngl, _that.getSngl())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (sngl != null ? sngl.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SwpOutBuilder {" +
				"sngl=" + this.sngl +
			'}';
		}
	}
}
