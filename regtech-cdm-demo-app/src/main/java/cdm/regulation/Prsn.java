package cdm.regulation;

import cdm.regulation.Othr;
import cdm.regulation.Prsn;
import cdm.regulation.Prsn.PrsnBuilder;
import cdm.regulation.Prsn.PrsnBuilderImpl;
import cdm.regulation.Prsn.PrsnImpl;
import cdm.regulation.meta.PrsnMeta;
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
@RosettaDataType(value="Prsn", builder=Prsn.PrsnBuilderImpl.class, version="${project.version}")
public interface Prsn extends RosettaModelObject {

	PrsnMeta metaData = new PrsnMeta();

	/*********************** Getter Methods  ***********************/
	String getCtryOfBrnch();
	Othr getOthr();

	/*********************** Build Methods  ***********************/
	Prsn build();
	
	Prsn.PrsnBuilder toBuilder();
	
	static Prsn.PrsnBuilder builder() {
		return new Prsn.PrsnBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Prsn> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Prsn> getType() {
		return Prsn.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("ctryOfBrnch"), String.class, getCtryOfBrnch(), this);
		processRosetta(path.newSubPath("othr"), processor, Othr.class, getOthr());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PrsnBuilder extends Prsn, RosettaModelObjectBuilder {
		Othr.OthrBuilder getOrCreateOthr();
		Othr.OthrBuilder getOthr();
		Prsn.PrsnBuilder setCtryOfBrnch(String ctryOfBrnch);
		Prsn.PrsnBuilder setOthr(Othr othr);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("ctryOfBrnch"), String.class, getCtryOfBrnch(), this);
			processRosetta(path.newSubPath("othr"), processor, Othr.OthrBuilder.class, getOthr());
		}
		

		Prsn.PrsnBuilder prune();
	}

	/*********************** Immutable Implementation of Prsn  ***********************/
	class PrsnImpl implements Prsn {
		private final String ctryOfBrnch;
		private final Othr othr;
		
		protected PrsnImpl(Prsn.PrsnBuilder builder) {
			this.ctryOfBrnch = builder.getCtryOfBrnch();
			this.othr = ofNullable(builder.getOthr()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("ctryOfBrnch")
		public String getCtryOfBrnch() {
			return ctryOfBrnch;
		}
		
		@Override
		@RosettaAttribute("othr")
		public Othr getOthr() {
			return othr;
		}
		
		@Override
		public Prsn build() {
			return this;
		}
		
		@Override
		public Prsn.PrsnBuilder toBuilder() {
			Prsn.PrsnBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Prsn.PrsnBuilder builder) {
			ofNullable(getCtryOfBrnch()).ifPresent(builder::setCtryOfBrnch);
			ofNullable(getOthr()).ifPresent(builder::setOthr);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Prsn _that = getType().cast(o);
		
			if (!Objects.equals(ctryOfBrnch, _that.getCtryOfBrnch())) return false;
			if (!Objects.equals(othr, _that.getOthr())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (ctryOfBrnch != null ? ctryOfBrnch.hashCode() : 0);
			_result = 31 * _result + (othr != null ? othr.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Prsn {" +
				"ctryOfBrnch=" + this.ctryOfBrnch + ", " +
				"othr=" + this.othr +
			'}';
		}
	}

	/*********************** Builder Implementation of Prsn  ***********************/
	class PrsnBuilderImpl implements Prsn.PrsnBuilder {
	
		protected String ctryOfBrnch;
		protected Othr.OthrBuilder othr;
	
		public PrsnBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("ctryOfBrnch")
		public String getCtryOfBrnch() {
			return ctryOfBrnch;
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
		@RosettaAttribute("ctryOfBrnch")
		public Prsn.PrsnBuilder setCtryOfBrnch(String ctryOfBrnch) {
			this.ctryOfBrnch = ctryOfBrnch==null?null:ctryOfBrnch;
			return this;
		}
		@Override
		@RosettaAttribute("othr")
		public Prsn.PrsnBuilder setOthr(Othr othr) {
			this.othr = othr==null?null:othr.toBuilder();
			return this;
		}
		
		@Override
		public Prsn build() {
			return new Prsn.PrsnImpl(this);
		}
		
		@Override
		public Prsn.PrsnBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Prsn.PrsnBuilder prune() {
			if (othr!=null && !othr.prune().hasData()) othr = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCtryOfBrnch()!=null) return true;
			if (getOthr()!=null && getOthr().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Prsn.PrsnBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Prsn.PrsnBuilder o = (Prsn.PrsnBuilder) other;
			
			merger.mergeRosetta(getOthr(), o.getOthr(), this::setOthr);
			
			merger.mergeBasic(getCtryOfBrnch(), o.getCtryOfBrnch(), this::setCtryOfBrnch);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Prsn _that = getType().cast(o);
		
			if (!Objects.equals(ctryOfBrnch, _that.getCtryOfBrnch())) return false;
			if (!Objects.equals(othr, _that.getOthr())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (ctryOfBrnch != null ? ctryOfBrnch.hashCode() : 0);
			_result = 31 * _result + (othr != null ? othr.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrsnBuilder {" +
				"ctryOfBrnch=" + this.ctryOfBrnch + ", " +
				"othr=" + this.othr +
			'}';
		}
	}
}
