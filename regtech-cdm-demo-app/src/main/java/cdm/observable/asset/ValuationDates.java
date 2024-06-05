package cdm.observable.asset;

import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.asset.ValuationDates;
import cdm.observable.asset.ValuationDates.ValuationDatesBuilder;
import cdm.observable.asset.ValuationDates.ValuationDatesBuilderImpl;
import cdm.observable.asset.ValuationDates.ValuationDatesImpl;
import cdm.observable.asset.meta.ValuationDatesMeta;
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
 * Defines how and when a performance type option or performance type swap is to be valued, including initial, interim and final valuation dates.
 * @version ${project.version}
 */
@RosettaDataType(value="ValuationDates", builder=ValuationDates.ValuationDatesBuilderImpl.class, version="${project.version}")
public interface ValuationDates extends RosettaModelObject {

	ValuationDatesMeta metaData = new ValuationDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the initial valuation dates of the underlier.
	 */
	PerformanceValuationDates getValuationDatesInitial();
	/**
	 * Specifies the interim valuation dates of the underlier.
	 */
	PerformanceValuationDates getValuationDatesInterim();
	/**
	 * Specifies the final valuation dates of the underlier.
	 */
	PerformanceValuationDates getValuationDatesFinal();

	/*********************** Build Methods  ***********************/
	ValuationDates build();
	
	ValuationDates.ValuationDatesBuilder toBuilder();
	
	static ValuationDates.ValuationDatesBuilder builder() {
		return new ValuationDates.ValuationDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ValuationDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ValuationDates> getType() {
		return ValuationDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("valuationDatesInitial"), processor, PerformanceValuationDates.class, getValuationDatesInitial());
		processRosetta(path.newSubPath("valuationDatesInterim"), processor, PerformanceValuationDates.class, getValuationDatesInterim());
		processRosetta(path.newSubPath("valuationDatesFinal"), processor, PerformanceValuationDates.class, getValuationDatesFinal());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ValuationDatesBuilder extends ValuationDates, RosettaModelObjectBuilder {
		PerformanceValuationDates.PerformanceValuationDatesBuilder getOrCreateValuationDatesInitial();
		PerformanceValuationDates.PerformanceValuationDatesBuilder getValuationDatesInitial();
		PerformanceValuationDates.PerformanceValuationDatesBuilder getOrCreateValuationDatesInterim();
		PerformanceValuationDates.PerformanceValuationDatesBuilder getValuationDatesInterim();
		PerformanceValuationDates.PerformanceValuationDatesBuilder getOrCreateValuationDatesFinal();
		PerformanceValuationDates.PerformanceValuationDatesBuilder getValuationDatesFinal();
		ValuationDates.ValuationDatesBuilder setValuationDatesInitial(PerformanceValuationDates valuationDatesInitial);
		ValuationDates.ValuationDatesBuilder setValuationDatesInterim(PerformanceValuationDates valuationDatesInterim);
		ValuationDates.ValuationDatesBuilder setValuationDatesFinal(PerformanceValuationDates valuationDatesFinal);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("valuationDatesInitial"), processor, PerformanceValuationDates.PerformanceValuationDatesBuilder.class, getValuationDatesInitial());
			processRosetta(path.newSubPath("valuationDatesInterim"), processor, PerformanceValuationDates.PerformanceValuationDatesBuilder.class, getValuationDatesInterim());
			processRosetta(path.newSubPath("valuationDatesFinal"), processor, PerformanceValuationDates.PerformanceValuationDatesBuilder.class, getValuationDatesFinal());
		}
		

		ValuationDates.ValuationDatesBuilder prune();
	}

	/*********************** Immutable Implementation of ValuationDates  ***********************/
	class ValuationDatesImpl implements ValuationDates {
		private final PerformanceValuationDates valuationDatesInitial;
		private final PerformanceValuationDates valuationDatesInterim;
		private final PerformanceValuationDates valuationDatesFinal;
		
		protected ValuationDatesImpl(ValuationDates.ValuationDatesBuilder builder) {
			this.valuationDatesInitial = ofNullable(builder.getValuationDatesInitial()).map(f->f.build()).orElse(null);
			this.valuationDatesInterim = ofNullable(builder.getValuationDatesInterim()).map(f->f.build()).orElse(null);
			this.valuationDatesFinal = ofNullable(builder.getValuationDatesFinal()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("valuationDatesInitial")
		public PerformanceValuationDates getValuationDatesInitial() {
			return valuationDatesInitial;
		}
		
		@Override
		@RosettaAttribute("valuationDatesInterim")
		public PerformanceValuationDates getValuationDatesInterim() {
			return valuationDatesInterim;
		}
		
		@Override
		@RosettaAttribute("valuationDatesFinal")
		public PerformanceValuationDates getValuationDatesFinal() {
			return valuationDatesFinal;
		}
		
		@Override
		public ValuationDates build() {
			return this;
		}
		
		@Override
		public ValuationDates.ValuationDatesBuilder toBuilder() {
			ValuationDates.ValuationDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ValuationDates.ValuationDatesBuilder builder) {
			ofNullable(getValuationDatesInitial()).ifPresent(builder::setValuationDatesInitial);
			ofNullable(getValuationDatesInterim()).ifPresent(builder::setValuationDatesInterim);
			ofNullable(getValuationDatesFinal()).ifPresent(builder::setValuationDatesFinal);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ValuationDates _that = getType().cast(o);
		
			if (!Objects.equals(valuationDatesInitial, _that.getValuationDatesInitial())) return false;
			if (!Objects.equals(valuationDatesInterim, _that.getValuationDatesInterim())) return false;
			if (!Objects.equals(valuationDatesFinal, _that.getValuationDatesFinal())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationDatesInitial != null ? valuationDatesInitial.hashCode() : 0);
			_result = 31 * _result + (valuationDatesInterim != null ? valuationDatesInterim.hashCode() : 0);
			_result = 31 * _result + (valuationDatesFinal != null ? valuationDatesFinal.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationDates {" +
				"valuationDatesInitial=" + this.valuationDatesInitial + ", " +
				"valuationDatesInterim=" + this.valuationDatesInterim + ", " +
				"valuationDatesFinal=" + this.valuationDatesFinal +
			'}';
		}
	}

	/*********************** Builder Implementation of ValuationDates  ***********************/
	class ValuationDatesBuilderImpl implements ValuationDates.ValuationDatesBuilder {
	
		protected PerformanceValuationDates.PerformanceValuationDatesBuilder valuationDatesInitial;
		protected PerformanceValuationDates.PerformanceValuationDatesBuilder valuationDatesInterim;
		protected PerformanceValuationDates.PerformanceValuationDatesBuilder valuationDatesFinal;
	
		public ValuationDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("valuationDatesInitial")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder getValuationDatesInitial() {
			return valuationDatesInitial;
		}
		
		@Override
		public PerformanceValuationDates.PerformanceValuationDatesBuilder getOrCreateValuationDatesInitial() {
			PerformanceValuationDates.PerformanceValuationDatesBuilder result;
			if (valuationDatesInitial!=null) {
				result = valuationDatesInitial;
			}
			else {
				result = valuationDatesInitial = PerformanceValuationDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationDatesInterim")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder getValuationDatesInterim() {
			return valuationDatesInterim;
		}
		
		@Override
		public PerformanceValuationDates.PerformanceValuationDatesBuilder getOrCreateValuationDatesInterim() {
			PerformanceValuationDates.PerformanceValuationDatesBuilder result;
			if (valuationDatesInterim!=null) {
				result = valuationDatesInterim;
			}
			else {
				result = valuationDatesInterim = PerformanceValuationDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationDatesFinal")
		public PerformanceValuationDates.PerformanceValuationDatesBuilder getValuationDatesFinal() {
			return valuationDatesFinal;
		}
		
		@Override
		public PerformanceValuationDates.PerformanceValuationDatesBuilder getOrCreateValuationDatesFinal() {
			PerformanceValuationDates.PerformanceValuationDatesBuilder result;
			if (valuationDatesFinal!=null) {
				result = valuationDatesFinal;
			}
			else {
				result = valuationDatesFinal = PerformanceValuationDates.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("valuationDatesInitial")
		public ValuationDates.ValuationDatesBuilder setValuationDatesInitial(PerformanceValuationDates valuationDatesInitial) {
			this.valuationDatesInitial = valuationDatesInitial==null?null:valuationDatesInitial.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationDatesInterim")
		public ValuationDates.ValuationDatesBuilder setValuationDatesInterim(PerformanceValuationDates valuationDatesInterim) {
			this.valuationDatesInterim = valuationDatesInterim==null?null:valuationDatesInterim.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationDatesFinal")
		public ValuationDates.ValuationDatesBuilder setValuationDatesFinal(PerformanceValuationDates valuationDatesFinal) {
			this.valuationDatesFinal = valuationDatesFinal==null?null:valuationDatesFinal.toBuilder();
			return this;
		}
		
		@Override
		public ValuationDates build() {
			return new ValuationDates.ValuationDatesImpl(this);
		}
		
		@Override
		public ValuationDates.ValuationDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ValuationDates.ValuationDatesBuilder prune() {
			if (valuationDatesInitial!=null && !valuationDatesInitial.prune().hasData()) valuationDatesInitial = null;
			if (valuationDatesInterim!=null && !valuationDatesInterim.prune().hasData()) valuationDatesInterim = null;
			if (valuationDatesFinal!=null && !valuationDatesFinal.prune().hasData()) valuationDatesFinal = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValuationDatesInitial()!=null && getValuationDatesInitial().hasData()) return true;
			if (getValuationDatesInterim()!=null && getValuationDatesInterim().hasData()) return true;
			if (getValuationDatesFinal()!=null && getValuationDatesFinal().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ValuationDates.ValuationDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ValuationDates.ValuationDatesBuilder o = (ValuationDates.ValuationDatesBuilder) other;
			
			merger.mergeRosetta(getValuationDatesInitial(), o.getValuationDatesInitial(), this::setValuationDatesInitial);
			merger.mergeRosetta(getValuationDatesInterim(), o.getValuationDatesInterim(), this::setValuationDatesInterim);
			merger.mergeRosetta(getValuationDatesFinal(), o.getValuationDatesFinal(), this::setValuationDatesFinal);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ValuationDates _that = getType().cast(o);
		
			if (!Objects.equals(valuationDatesInitial, _that.getValuationDatesInitial())) return false;
			if (!Objects.equals(valuationDatesInterim, _that.getValuationDatesInterim())) return false;
			if (!Objects.equals(valuationDatesFinal, _that.getValuationDatesFinal())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationDatesInitial != null ? valuationDatesInitial.hashCode() : 0);
			_result = 31 * _result + (valuationDatesInterim != null ? valuationDatesInterim.hashCode() : 0);
			_result = 31 * _result + (valuationDatesFinal != null ? valuationDatesFinal.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationDatesBuilder {" +
				"valuationDatesInitial=" + this.valuationDatesInitial + ", " +
				"valuationDatesInterim=" + this.valuationDatesInterim + ", " +
				"valuationDatesFinal=" + this.valuationDatesFinal +
			'}';
		}
	}
}
