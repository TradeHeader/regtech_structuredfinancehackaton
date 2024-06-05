package cdm.regulation;

import cdm.regulation.Term;
import cdm.regulation.Term.TermBuilder;
import cdm.regulation.Term.TermBuilderImpl;
import cdm.regulation.Term.TermImpl;
import cdm.regulation.meta.TermMeta;
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
@RosettaDataType(value="Term", builder=Term.TermBuilderImpl.class, version="${project.version}")
public interface Term extends RosettaModelObject {

	TermMeta metaData = new TermMeta();

	/*********************** Getter Methods  ***********************/
	String getUnit();
	String getVal();

	/*********************** Build Methods  ***********************/
	Term build();
	
	Term.TermBuilder toBuilder();
	
	static Term.TermBuilder builder() {
		return new Term.TermBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Term> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Term> getType() {
		return Term.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("unit"), String.class, getUnit(), this);
		processor.processBasic(path.newSubPath("val"), String.class, getVal(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TermBuilder extends Term, RosettaModelObjectBuilder {
		Term.TermBuilder setUnit(String unit);
		Term.TermBuilder setVal(String val);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("unit"), String.class, getUnit(), this);
			processor.processBasic(path.newSubPath("val"), String.class, getVal(), this);
		}
		

		Term.TermBuilder prune();
	}

	/*********************** Immutable Implementation of Term  ***********************/
	class TermImpl implements Term {
		private final String unit;
		private final String val;
		
		protected TermImpl(Term.TermBuilder builder) {
			this.unit = builder.getUnit();
			this.val = builder.getVal();
		}
		
		@Override
		@RosettaAttribute("unit")
		public String getUnit() {
			return unit;
		}
		
		@Override
		@RosettaAttribute("val")
		public String getVal() {
			return val;
		}
		
		@Override
		public Term build() {
			return this;
		}
		
		@Override
		public Term.TermBuilder toBuilder() {
			Term.TermBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Term.TermBuilder builder) {
			ofNullable(getUnit()).ifPresent(builder::setUnit);
			ofNullable(getVal()).ifPresent(builder::setVal);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Term _that = getType().cast(o);
		
			if (!Objects.equals(unit, _that.getUnit())) return false;
			if (!Objects.equals(val, _that.getVal())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unit != null ? unit.hashCode() : 0);
			_result = 31 * _result + (val != null ? val.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Term {" +
				"unit=" + this.unit + ", " +
				"val=" + this.val +
			'}';
		}
	}

	/*********************** Builder Implementation of Term  ***********************/
	class TermBuilderImpl implements Term.TermBuilder {
	
		protected String unit;
		protected String val;
	
		public TermBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("unit")
		public String getUnit() {
			return unit;
		}
		
		@Override
		@RosettaAttribute("val")
		public String getVal() {
			return val;
		}
		
	
		@Override
		@RosettaAttribute("unit")
		public Term.TermBuilder setUnit(String unit) {
			this.unit = unit==null?null:unit;
			return this;
		}
		@Override
		@RosettaAttribute("val")
		public Term.TermBuilder setVal(String val) {
			this.val = val==null?null:val;
			return this;
		}
		
		@Override
		public Term build() {
			return new Term.TermImpl(this);
		}
		
		@Override
		public Term.TermBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Term.TermBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getUnit()!=null) return true;
			if (getVal()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Term.TermBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Term.TermBuilder o = (Term.TermBuilder) other;
			
			
			merger.mergeBasic(getUnit(), o.getUnit(), this::setUnit);
			merger.mergeBasic(getVal(), o.getVal(), this::setVal);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Term _that = getType().cast(o);
		
			if (!Objects.equals(unit, _that.getUnit())) return false;
			if (!Objects.equals(val, _that.getVal())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unit != null ? unit.hashCode() : 0);
			_result = 31 * _result + (val != null ? val.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TermBuilder {" +
				"unit=" + this.unit + ", " +
				"val=" + this.val +
			'}';
		}
	}
}
