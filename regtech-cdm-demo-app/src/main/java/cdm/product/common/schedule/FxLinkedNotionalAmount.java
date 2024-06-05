package cdm.product.common.schedule;

import cdm.product.common.schedule.FxLinkedNotionalAmount;
import cdm.product.common.schedule.FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder;
import cdm.product.common.schedule.FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilderImpl;
import cdm.product.common.schedule.FxLinkedNotionalAmount.FxLinkedNotionalAmountImpl;
import cdm.product.common.schedule.meta.FxLinkedNotionalAmountMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A data to:  describe the cashflow representation for FX linked notionals.
 * @version ${project.version}
 */
@RosettaDataType(value="FxLinkedNotionalAmount", builder=FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilderImpl.class, version="${project.version}")
public interface FxLinkedNotionalAmount extends RosettaModelObject {

	FxLinkedNotionalAmountMeta metaData = new FxLinkedNotionalAmountMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The reset date.
	 */
	Date getResetDate();
	/**
	 * The date on which the FX spot rate is observed. This date should already be adjusted for any applicable business day convention.
	 */
	Date getAdjustedFxSpotFixingDate();
	/**
	 * The actual observed FX spot rate.
	 */
	BigDecimal getObservedFxSpotRate();
	/**
	 * The calculation period notional amount.
	 */
	BigDecimal getNotionalAmount();

	/*********************** Build Methods  ***********************/
	FxLinkedNotionalAmount build();
	
	FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder toBuilder();
	
	static FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder builder() {
		return new FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FxLinkedNotionalAmount> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FxLinkedNotionalAmount> getType() {
		return FxLinkedNotionalAmount.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("resetDate"), Date.class, getResetDate(), this);
		processor.processBasic(path.newSubPath("adjustedFxSpotFixingDate"), Date.class, getAdjustedFxSpotFixingDate(), this);
		processor.processBasic(path.newSubPath("observedFxSpotRate"), BigDecimal.class, getObservedFxSpotRate(), this);
		processor.processBasic(path.newSubPath("notionalAmount"), BigDecimal.class, getNotionalAmount(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FxLinkedNotionalAmountBuilder extends FxLinkedNotionalAmount, RosettaModelObjectBuilder {
		FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder setResetDate(Date resetDate);
		FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder setAdjustedFxSpotFixingDate(Date adjustedFxSpotFixingDate);
		FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder setObservedFxSpotRate(BigDecimal observedFxSpotRate);
		FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder setNotionalAmount(BigDecimal notionalAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("resetDate"), Date.class, getResetDate(), this);
			processor.processBasic(path.newSubPath("adjustedFxSpotFixingDate"), Date.class, getAdjustedFxSpotFixingDate(), this);
			processor.processBasic(path.newSubPath("observedFxSpotRate"), BigDecimal.class, getObservedFxSpotRate(), this);
			processor.processBasic(path.newSubPath("notionalAmount"), BigDecimal.class, getNotionalAmount(), this);
		}
		

		FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder prune();
	}

	/*********************** Immutable Implementation of FxLinkedNotionalAmount  ***********************/
	class FxLinkedNotionalAmountImpl implements FxLinkedNotionalAmount {
		private final Date resetDate;
		private final Date adjustedFxSpotFixingDate;
		private final BigDecimal observedFxSpotRate;
		private final BigDecimal notionalAmount;
		
		protected FxLinkedNotionalAmountImpl(FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder builder) {
			this.resetDate = builder.getResetDate();
			this.adjustedFxSpotFixingDate = builder.getAdjustedFxSpotFixingDate();
			this.observedFxSpotRate = builder.getObservedFxSpotRate();
			this.notionalAmount = builder.getNotionalAmount();
		}
		
		@Override
		@RosettaAttribute("resetDate")
		public Date getResetDate() {
			return resetDate;
		}
		
		@Override
		@RosettaAttribute("adjustedFxSpotFixingDate")
		public Date getAdjustedFxSpotFixingDate() {
			return adjustedFxSpotFixingDate;
		}
		
		@Override
		@RosettaAttribute("observedFxSpotRate")
		public BigDecimal getObservedFxSpotRate() {
			return observedFxSpotRate;
		}
		
		@Override
		@RosettaAttribute("notionalAmount")
		public BigDecimal getNotionalAmount() {
			return notionalAmount;
		}
		
		@Override
		public FxLinkedNotionalAmount build() {
			return this;
		}
		
		@Override
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder toBuilder() {
			FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder builder) {
			ofNullable(getResetDate()).ifPresent(builder::setResetDate);
			ofNullable(getAdjustedFxSpotFixingDate()).ifPresent(builder::setAdjustedFxSpotFixingDate);
			ofNullable(getObservedFxSpotRate()).ifPresent(builder::setObservedFxSpotRate);
			ofNullable(getNotionalAmount()).ifPresent(builder::setNotionalAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxLinkedNotionalAmount _that = getType().cast(o);
		
			if (!Objects.equals(resetDate, _that.getResetDate())) return false;
			if (!Objects.equals(adjustedFxSpotFixingDate, _that.getAdjustedFxSpotFixingDate())) return false;
			if (!Objects.equals(observedFxSpotRate, _that.getObservedFxSpotRate())) return false;
			if (!Objects.equals(notionalAmount, _that.getNotionalAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (resetDate != null ? resetDate.hashCode() : 0);
			_result = 31 * _result + (adjustedFxSpotFixingDate != null ? adjustedFxSpotFixingDate.hashCode() : 0);
			_result = 31 * _result + (observedFxSpotRate != null ? observedFxSpotRate.hashCode() : 0);
			_result = 31 * _result + (notionalAmount != null ? notionalAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxLinkedNotionalAmount {" +
				"resetDate=" + this.resetDate + ", " +
				"adjustedFxSpotFixingDate=" + this.adjustedFxSpotFixingDate + ", " +
				"observedFxSpotRate=" + this.observedFxSpotRate + ", " +
				"notionalAmount=" + this.notionalAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of FxLinkedNotionalAmount  ***********************/
	class FxLinkedNotionalAmountBuilderImpl implements FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder {
	
		protected Date resetDate;
		protected Date adjustedFxSpotFixingDate;
		protected BigDecimal observedFxSpotRate;
		protected BigDecimal notionalAmount;
	
		public FxLinkedNotionalAmountBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("resetDate")
		public Date getResetDate() {
			return resetDate;
		}
		
		@Override
		@RosettaAttribute("adjustedFxSpotFixingDate")
		public Date getAdjustedFxSpotFixingDate() {
			return adjustedFxSpotFixingDate;
		}
		
		@Override
		@RosettaAttribute("observedFxSpotRate")
		public BigDecimal getObservedFxSpotRate() {
			return observedFxSpotRate;
		}
		
		@Override
		@RosettaAttribute("notionalAmount")
		public BigDecimal getNotionalAmount() {
			return notionalAmount;
		}
		
	
		@Override
		@RosettaAttribute("resetDate")
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder setResetDate(Date resetDate) {
			this.resetDate = resetDate==null?null:resetDate;
			return this;
		}
		@Override
		@RosettaAttribute("adjustedFxSpotFixingDate")
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder setAdjustedFxSpotFixingDate(Date adjustedFxSpotFixingDate) {
			this.adjustedFxSpotFixingDate = adjustedFxSpotFixingDate==null?null:adjustedFxSpotFixingDate;
			return this;
		}
		@Override
		@RosettaAttribute("observedFxSpotRate")
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder setObservedFxSpotRate(BigDecimal observedFxSpotRate) {
			this.observedFxSpotRate = observedFxSpotRate==null?null:observedFxSpotRate;
			return this;
		}
		@Override
		@RosettaAttribute("notionalAmount")
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder setNotionalAmount(BigDecimal notionalAmount) {
			this.notionalAmount = notionalAmount==null?null:notionalAmount;
			return this;
		}
		
		@Override
		public FxLinkedNotionalAmount build() {
			return new FxLinkedNotionalAmount.FxLinkedNotionalAmountImpl(this);
		}
		
		@Override
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getResetDate()!=null) return true;
			if (getAdjustedFxSpotFixingDate()!=null) return true;
			if (getObservedFxSpotRate()!=null) return true;
			if (getNotionalAmount()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder o = (FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder) other;
			
			
			merger.mergeBasic(getResetDate(), o.getResetDate(), this::setResetDate);
			merger.mergeBasic(getAdjustedFxSpotFixingDate(), o.getAdjustedFxSpotFixingDate(), this::setAdjustedFxSpotFixingDate);
			merger.mergeBasic(getObservedFxSpotRate(), o.getObservedFxSpotRate(), this::setObservedFxSpotRate);
			merger.mergeBasic(getNotionalAmount(), o.getNotionalAmount(), this::setNotionalAmount);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxLinkedNotionalAmount _that = getType().cast(o);
		
			if (!Objects.equals(resetDate, _that.getResetDate())) return false;
			if (!Objects.equals(adjustedFxSpotFixingDate, _that.getAdjustedFxSpotFixingDate())) return false;
			if (!Objects.equals(observedFxSpotRate, _that.getObservedFxSpotRate())) return false;
			if (!Objects.equals(notionalAmount, _that.getNotionalAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (resetDate != null ? resetDate.hashCode() : 0);
			_result = 31 * _result + (adjustedFxSpotFixingDate != null ? adjustedFxSpotFixingDate.hashCode() : 0);
			_result = 31 * _result + (observedFxSpotRate != null ? observedFxSpotRate.hashCode() : 0);
			_result = 31 * _result + (notionalAmount != null ? notionalAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxLinkedNotionalAmountBuilder {" +
				"resetDate=" + this.resetDate + ", " +
				"adjustedFxSpotFixingDate=" + this.adjustedFxSpotFixingDate + ", " +
				"observedFxSpotRate=" + this.observedFxSpotRate + ", " +
				"notionalAmount=" + this.notionalAmount +
			'}';
		}
	}
}
