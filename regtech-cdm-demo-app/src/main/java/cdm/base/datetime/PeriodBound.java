package cdm.base.datetime;

import cdm.base.datetime.Period;
import cdm.base.datetime.PeriodBound;
import cdm.base.datetime.PeriodBound.PeriodBoundBuilder;
import cdm.base.datetime.PeriodBound.PeriodBoundBuilderImpl;
import cdm.base.datetime.PeriodBound.PeriodBoundImpl;
import cdm.base.datetime.meta.PeriodBoundMeta;
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
 * Indicator to specify if the period bound is defined as a period and whether the bound is inclusive.
 * @version ${project.version}
 */
@RosettaDataType(value="PeriodBound", builder=PeriodBound.PeriodBoundBuilderImpl.class, version="${project.version}")
public interface PeriodBound extends RosettaModelObject {

	PeriodBoundMeta metaData = new PeriodBoundMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the period is to be used as the bound, e.g. 5Y.
	 */
	Period getPeriod();
	/**
	 * Specifies whether the period bound is inclusive, e.g. for a lower bound, false would indicate greater than, whereas true would indicate greater than or equal to.
	 */
	Boolean getInclusive();

	/*********************** Build Methods  ***********************/
	PeriodBound build();
	
	PeriodBound.PeriodBoundBuilder toBuilder();
	
	static PeriodBound.PeriodBoundBuilder builder() {
		return new PeriodBound.PeriodBoundBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PeriodBound> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PeriodBound> getType() {
		return PeriodBound.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("period"), processor, Period.class, getPeriod());
		processor.processBasic(path.newSubPath("inclusive"), Boolean.class, getInclusive(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PeriodBoundBuilder extends PeriodBound, RosettaModelObjectBuilder {
		Period.PeriodBuilder getOrCreatePeriod();
		Period.PeriodBuilder getPeriod();
		PeriodBound.PeriodBoundBuilder setPeriod(Period period);
		PeriodBound.PeriodBoundBuilder setInclusive(Boolean inclusive);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("period"), processor, Period.PeriodBuilder.class, getPeriod());
			processor.processBasic(path.newSubPath("inclusive"), Boolean.class, getInclusive(), this);
		}
		

		PeriodBound.PeriodBoundBuilder prune();
	}

	/*********************** Immutable Implementation of PeriodBound  ***********************/
	class PeriodBoundImpl implements PeriodBound {
		private final Period period;
		private final Boolean inclusive;
		
		protected PeriodBoundImpl(PeriodBound.PeriodBoundBuilder builder) {
			this.period = ofNullable(builder.getPeriod()).map(f->f.build()).orElse(null);
			this.inclusive = builder.getInclusive();
		}
		
		@Override
		@RosettaAttribute("period")
		public Period getPeriod() {
			return period;
		}
		
		@Override
		@RosettaAttribute("inclusive")
		public Boolean getInclusive() {
			return inclusive;
		}
		
		@Override
		public PeriodBound build() {
			return this;
		}
		
		@Override
		public PeriodBound.PeriodBoundBuilder toBuilder() {
			PeriodBound.PeriodBoundBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PeriodBound.PeriodBoundBuilder builder) {
			ofNullable(getPeriod()).ifPresent(builder::setPeriod);
			ofNullable(getInclusive()).ifPresent(builder::setInclusive);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PeriodBound _that = getType().cast(o);
		
			if (!Objects.equals(period, _that.getPeriod())) return false;
			if (!Objects.equals(inclusive, _that.getInclusive())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (period != null ? period.hashCode() : 0);
			_result = 31 * _result + (inclusive != null ? inclusive.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PeriodBound {" +
				"period=" + this.period + ", " +
				"inclusive=" + this.inclusive +
			'}';
		}
	}

	/*********************** Builder Implementation of PeriodBound  ***********************/
	class PeriodBoundBuilderImpl implements PeriodBound.PeriodBoundBuilder {
	
		protected Period.PeriodBuilder period;
		protected Boolean inclusive;
	
		public PeriodBoundBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("period")
		public Period.PeriodBuilder getPeriod() {
			return period;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreatePeriod() {
			Period.PeriodBuilder result;
			if (period!=null) {
				result = period;
			}
			else {
				result = period = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("inclusive")
		public Boolean getInclusive() {
			return inclusive;
		}
		
	
		@Override
		@RosettaAttribute("period")
		public PeriodBound.PeriodBoundBuilder setPeriod(Period period) {
			this.period = period==null?null:period.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("inclusive")
		public PeriodBound.PeriodBoundBuilder setInclusive(Boolean inclusive) {
			this.inclusive = inclusive==null?null:inclusive;
			return this;
		}
		
		@Override
		public PeriodBound build() {
			return new PeriodBound.PeriodBoundImpl(this);
		}
		
		@Override
		public PeriodBound.PeriodBoundBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PeriodBound.PeriodBoundBuilder prune() {
			if (period!=null && !period.prune().hasData()) period = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPeriod()!=null && getPeriod().hasData()) return true;
			if (getInclusive()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PeriodBound.PeriodBoundBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PeriodBound.PeriodBoundBuilder o = (PeriodBound.PeriodBoundBuilder) other;
			
			merger.mergeRosetta(getPeriod(), o.getPeriod(), this::setPeriod);
			
			merger.mergeBasic(getInclusive(), o.getInclusive(), this::setInclusive);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PeriodBound _that = getType().cast(o);
		
			if (!Objects.equals(period, _that.getPeriod())) return false;
			if (!Objects.equals(inclusive, _that.getInclusive())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (period != null ? period.hashCode() : 0);
			_result = 31 * _result + (inclusive != null ? inclusive.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PeriodBoundBuilder {" +
				"period=" + this.period + ", " +
				"inclusive=" + this.inclusive +
			'}';
		}
	}
}
