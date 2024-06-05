package cdm.regulation;

import cdm.regulation.FinInstrm;
import cdm.regulation.FinInstrm.FinInstrmBuilder;
import cdm.regulation.FinInstrm.FinInstrmBuilderImpl;
import cdm.regulation.FinInstrm.FinInstrmImpl;
import cdm.regulation.Othr;
import cdm.regulation.meta.FinInstrmMeta;
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
@RosettaDataType(value="FinInstrm", builder=FinInstrm.FinInstrmBuilderImpl.class, version="${project.version}")
public interface FinInstrm extends RosettaModelObject {

	FinInstrmMeta metaData = new FinInstrmMeta();

	/*********************** Getter Methods  ***********************/
	Othr getOthr();

	/*********************** Build Methods  ***********************/
	FinInstrm build();
	
	FinInstrm.FinInstrmBuilder toBuilder();
	
	static FinInstrm.FinInstrmBuilder builder() {
		return new FinInstrm.FinInstrmBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FinInstrm> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FinInstrm> getType() {
		return FinInstrm.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("othr"), processor, Othr.class, getOthr());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FinInstrmBuilder extends FinInstrm, RosettaModelObjectBuilder {
		Othr.OthrBuilder getOrCreateOthr();
		Othr.OthrBuilder getOthr();
		FinInstrm.FinInstrmBuilder setOthr(Othr othr);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("othr"), processor, Othr.OthrBuilder.class, getOthr());
		}
		

		FinInstrm.FinInstrmBuilder prune();
	}

	/*********************** Immutable Implementation of FinInstrm  ***********************/
	class FinInstrmImpl implements FinInstrm {
		private final Othr othr;
		
		protected FinInstrmImpl(FinInstrm.FinInstrmBuilder builder) {
			this.othr = ofNullable(builder.getOthr()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("othr")
		public Othr getOthr() {
			return othr;
		}
		
		@Override
		public FinInstrm build() {
			return this;
		}
		
		@Override
		public FinInstrm.FinInstrmBuilder toBuilder() {
			FinInstrm.FinInstrmBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FinInstrm.FinInstrmBuilder builder) {
			ofNullable(getOthr()).ifPresent(builder::setOthr);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FinInstrm _that = getType().cast(o);
		
			if (!Objects.equals(othr, _that.getOthr())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (othr != null ? othr.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FinInstrm {" +
				"othr=" + this.othr +
			'}';
		}
	}

	/*********************** Builder Implementation of FinInstrm  ***********************/
	class FinInstrmBuilderImpl implements FinInstrm.FinInstrmBuilder {
	
		protected Othr.OthrBuilder othr;
	
		public FinInstrmBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("othr")
		public Othr.OthrBuilder getOthr() {
			return othr;
		}
		
		@Override
		public Othr.OthrBuilder getOrCreateOthr() {
			Othr.OthrBuilder result;
			if (othr!=null) {
				result = othr;
			}
			else {
				result = othr = Othr.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("othr")
		public FinInstrm.FinInstrmBuilder setOthr(Othr othr) {
			this.othr = othr==null?null:othr.toBuilder();
			return this;
		}
		
		@Override
		public FinInstrm build() {
			return new FinInstrm.FinInstrmImpl(this);
		}
		
		@Override
		public FinInstrm.FinInstrmBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FinInstrm.FinInstrmBuilder prune() {
			if (othr!=null && !othr.prune().hasData()) othr = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getOthr()!=null && getOthr().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FinInstrm.FinInstrmBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FinInstrm.FinInstrmBuilder o = (FinInstrm.FinInstrmBuilder) other;
			
			merger.mergeRosetta(getOthr(), o.getOthr(), this::setOthr);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FinInstrm _that = getType().cast(o);
		
			if (!Objects.equals(othr, _that.getOthr())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (othr != null ? othr.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FinInstrmBuilder {" +
				"othr=" + this.othr +
			'}';
		}
	}
}
