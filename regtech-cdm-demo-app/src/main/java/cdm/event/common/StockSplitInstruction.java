package cdm.event.common;

import cdm.event.common.StockSplitInstruction;
import cdm.event.common.StockSplitInstruction.StockSplitInstructionBuilder;
import cdm.event.common.StockSplitInstruction.StockSplitInstructionBuilderImpl;
import cdm.event.common.StockSplitInstruction.StockSplitInstructionImpl;
import cdm.event.common.meta.StockSplitInstructionMeta;
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
 * Data required to perform a stock split business event.
 * @version ${project.version}
 */
@RosettaDataType(value="StockSplitInstruction", builder=StockSplitInstruction.StockSplitInstructionBuilderImpl.class, version="${project.version}")
public interface StockSplitInstruction extends RosettaModelObject {

	StockSplitInstructionMeta metaData = new StockSplitInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The number that denotes the cumulative quantity of post-split shares issued to shareholders versus the quantity of pre-split shares previously issued to shareholders.  This number will be multiplied by existing shares in an equity derivative contract or other positions to determine the post-split number of shares.  With regard to any reference to price, the pre-split reference price will be divided by this number to determine the post-split reference price.
	 */
	BigDecimal getAdjustmentRatio();
	/**
	 * The effective date of the stock split, also known as the ex-date. This is the date on which the additional shares are paid to the shareholders, or in the case of a reverse stock split, the number shares held by each shareholder is proportionally reduced.  Equity derivative transactions can be amended in firms&#39; internal systems on such date.   In most markets, the listed stock price is reduced (or increased for a reverse stock split) to account for the split on the same date, but in some markets the price adjustment occurs on a later date.  In either case, equity derivative transactions should be amended on the date that the stocks are paid to the shareholders (or consolidated).
	 */
	Date getEffectiveDate();

	/*********************** Build Methods  ***********************/
	StockSplitInstruction build();
	
	StockSplitInstruction.StockSplitInstructionBuilder toBuilder();
	
	static StockSplitInstruction.StockSplitInstructionBuilder builder() {
		return new StockSplitInstruction.StockSplitInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends StockSplitInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends StockSplitInstruction> getType() {
		return StockSplitInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("adjustmentRatio"), BigDecimal.class, getAdjustmentRatio(), this);
		processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface StockSplitInstructionBuilder extends StockSplitInstruction, RosettaModelObjectBuilder {
		StockSplitInstruction.StockSplitInstructionBuilder setAdjustmentRatio(BigDecimal adjustmentRatio);
		StockSplitInstruction.StockSplitInstructionBuilder setEffectiveDate(Date effectiveDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("adjustmentRatio"), BigDecimal.class, getAdjustmentRatio(), this);
			processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
		}
		

		StockSplitInstruction.StockSplitInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of StockSplitInstruction  ***********************/
	class StockSplitInstructionImpl implements StockSplitInstruction {
		private final BigDecimal adjustmentRatio;
		private final Date effectiveDate;
		
		protected StockSplitInstructionImpl(StockSplitInstruction.StockSplitInstructionBuilder builder) {
			this.adjustmentRatio = builder.getAdjustmentRatio();
			this.effectiveDate = builder.getEffectiveDate();
		}
		
		@Override
		@RosettaAttribute("adjustmentRatio")
		public BigDecimal getAdjustmentRatio() {
			return adjustmentRatio;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		public StockSplitInstruction build() {
			return this;
		}
		
		@Override
		public StockSplitInstruction.StockSplitInstructionBuilder toBuilder() {
			StockSplitInstruction.StockSplitInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(StockSplitInstruction.StockSplitInstructionBuilder builder) {
			ofNullable(getAdjustmentRatio()).ifPresent(builder::setAdjustmentRatio);
			ofNullable(getEffectiveDate()).ifPresent(builder::setEffectiveDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StockSplitInstruction _that = getType().cast(o);
		
			if (!Objects.equals(adjustmentRatio, _that.getAdjustmentRatio())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustmentRatio != null ? adjustmentRatio.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StockSplitInstruction {" +
				"adjustmentRatio=" + this.adjustmentRatio + ", " +
				"effectiveDate=" + this.effectiveDate +
			'}';
		}
	}

	/*********************** Builder Implementation of StockSplitInstruction  ***********************/
	class StockSplitInstructionBuilderImpl implements StockSplitInstruction.StockSplitInstructionBuilder {
	
		protected BigDecimal adjustmentRatio;
		protected Date effectiveDate;
	
		public StockSplitInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("adjustmentRatio")
		public BigDecimal getAdjustmentRatio() {
			return adjustmentRatio;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
	
		@Override
		@RosettaAttribute("adjustmentRatio")
		public StockSplitInstruction.StockSplitInstructionBuilder setAdjustmentRatio(BigDecimal adjustmentRatio) {
			this.adjustmentRatio = adjustmentRatio==null?null:adjustmentRatio;
			return this;
		}
		@Override
		@RosettaAttribute("effectiveDate")
		public StockSplitInstruction.StockSplitInstructionBuilder setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate==null?null:effectiveDate;
			return this;
		}
		
		@Override
		public StockSplitInstruction build() {
			return new StockSplitInstruction.StockSplitInstructionImpl(this);
		}
		
		@Override
		public StockSplitInstruction.StockSplitInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StockSplitInstruction.StockSplitInstructionBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAdjustmentRatio()!=null) return true;
			if (getEffectiveDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StockSplitInstruction.StockSplitInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			StockSplitInstruction.StockSplitInstructionBuilder o = (StockSplitInstruction.StockSplitInstructionBuilder) other;
			
			
			merger.mergeBasic(getAdjustmentRatio(), o.getAdjustmentRatio(), this::setAdjustmentRatio);
			merger.mergeBasic(getEffectiveDate(), o.getEffectiveDate(), this::setEffectiveDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StockSplitInstruction _that = getType().cast(o);
		
			if (!Objects.equals(adjustmentRatio, _that.getAdjustmentRatio())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustmentRatio != null ? adjustmentRatio.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StockSplitInstructionBuilder {" +
				"adjustmentRatio=" + this.adjustmentRatio + ", " +
				"effectiveDate=" + this.effectiveDate +
			'}';
		}
	}
}
