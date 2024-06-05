package cdm.event.common;

import cdm.event.common.BillingRecord;
import cdm.event.common.BillingRecord.BillingRecordBuilder;
import cdm.event.common.BillingRecord.BillingRecordBuilderImpl;
import cdm.event.common.BillingRecord.BillingRecordImpl;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.meta.BillingRecordMeta;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder;
import cdm.observable.asset.Money;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies individual records within a billing invoice.
 * @version ${project.version}
 */
@RosettaDataType(value="BillingRecord", builder=BillingRecord.BillingRecordBuilderImpl.class, version="${project.version}")
public interface BillingRecord extends RosettaModelObject {

	BillingRecordMeta metaData = new BillingRecordMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The trade for the individual billing record.
	 */
	ReferenceWithMetaTradeState getTradeState();
	/**
	 * The settlement terms for the billing record
	 */
	Transfer getRecordTransfer();
	/**
	 * The starting date of the period described by this record
	 */
	Date getRecordStartDate();
	/**
	 * The ending date of the period described by this record
	 */
	Date getRecordEndDate();
	/**
	 * Indicates the minimum fee amount applied to the billing record, if any.
	 */
	Money getMinimumFee();

	/*********************** Build Methods  ***********************/
	BillingRecord build();
	
	BillingRecord.BillingRecordBuilder toBuilder();
	
	static BillingRecord.BillingRecordBuilder builder() {
		return new BillingRecord.BillingRecordBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BillingRecord> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BillingRecord> getType() {
		return BillingRecord.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("tradeState"), processor, ReferenceWithMetaTradeState.class, getTradeState());
		processRosetta(path.newSubPath("recordTransfer"), processor, Transfer.class, getRecordTransfer());
		processor.processBasic(path.newSubPath("recordStartDate"), Date.class, getRecordStartDate(), this);
		processor.processBasic(path.newSubPath("recordEndDate"), Date.class, getRecordEndDate(), this);
		processRosetta(path.newSubPath("minimumFee"), processor, Money.class, getMinimumFee());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BillingRecordBuilder extends BillingRecord, RosettaModelObjectBuilder {
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateTradeState();
		ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getTradeState();
		Transfer.TransferBuilder getOrCreateRecordTransfer();
		Transfer.TransferBuilder getRecordTransfer();
		Money.MoneyBuilder getOrCreateMinimumFee();
		Money.MoneyBuilder getMinimumFee();
		BillingRecord.BillingRecordBuilder setTradeState(ReferenceWithMetaTradeState tradeState0);
		BillingRecord.BillingRecordBuilder setTradeStateValue(TradeState tradeState1);
		BillingRecord.BillingRecordBuilder setRecordTransfer(Transfer recordTransfer);
		BillingRecord.BillingRecordBuilder setRecordStartDate(Date recordStartDate);
		BillingRecord.BillingRecordBuilder setRecordEndDate(Date recordEndDate);
		BillingRecord.BillingRecordBuilder setMinimumFee(Money minimumFee);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("tradeState"), processor, ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder.class, getTradeState());
			processRosetta(path.newSubPath("recordTransfer"), processor, Transfer.TransferBuilder.class, getRecordTransfer());
			processor.processBasic(path.newSubPath("recordStartDate"), Date.class, getRecordStartDate(), this);
			processor.processBasic(path.newSubPath("recordEndDate"), Date.class, getRecordEndDate(), this);
			processRosetta(path.newSubPath("minimumFee"), processor, Money.MoneyBuilder.class, getMinimumFee());
		}
		

		BillingRecord.BillingRecordBuilder prune();
	}

	/*********************** Immutable Implementation of BillingRecord  ***********************/
	class BillingRecordImpl implements BillingRecord {
		private final ReferenceWithMetaTradeState tradeState;
		private final Transfer recordTransfer;
		private final Date recordStartDate;
		private final Date recordEndDate;
		private final Money minimumFee;
		
		protected BillingRecordImpl(BillingRecord.BillingRecordBuilder builder) {
			this.tradeState = ofNullable(builder.getTradeState()).map(f->f.build()).orElse(null);
			this.recordTransfer = ofNullable(builder.getRecordTransfer()).map(f->f.build()).orElse(null);
			this.recordStartDate = builder.getRecordStartDate();
			this.recordEndDate = builder.getRecordEndDate();
			this.minimumFee = ofNullable(builder.getMinimumFee()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("tradeState")
		public ReferenceWithMetaTradeState getTradeState() {
			return tradeState;
		}
		
		@Override
		@RosettaAttribute("recordTransfer")
		public Transfer getRecordTransfer() {
			return recordTransfer;
		}
		
		@Override
		@RosettaAttribute("recordStartDate")
		public Date getRecordStartDate() {
			return recordStartDate;
		}
		
		@Override
		@RosettaAttribute("recordEndDate")
		public Date getRecordEndDate() {
			return recordEndDate;
		}
		
		@Override
		@RosettaAttribute("minimumFee")
		public Money getMinimumFee() {
			return minimumFee;
		}
		
		@Override
		public BillingRecord build() {
			return this;
		}
		
		@Override
		public BillingRecord.BillingRecordBuilder toBuilder() {
			BillingRecord.BillingRecordBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BillingRecord.BillingRecordBuilder builder) {
			ofNullable(getTradeState()).ifPresent(builder::setTradeState);
			ofNullable(getRecordTransfer()).ifPresent(builder::setRecordTransfer);
			ofNullable(getRecordStartDate()).ifPresent(builder::setRecordStartDate);
			ofNullable(getRecordEndDate()).ifPresent(builder::setRecordEndDate);
			ofNullable(getMinimumFee()).ifPresent(builder::setMinimumFee);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BillingRecord _that = getType().cast(o);
		
			if (!Objects.equals(tradeState, _that.getTradeState())) return false;
			if (!Objects.equals(recordTransfer, _that.getRecordTransfer())) return false;
			if (!Objects.equals(recordStartDate, _that.getRecordStartDate())) return false;
			if (!Objects.equals(recordEndDate, _that.getRecordEndDate())) return false;
			if (!Objects.equals(minimumFee, _that.getMinimumFee())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradeState != null ? tradeState.hashCode() : 0);
			_result = 31 * _result + (recordTransfer != null ? recordTransfer.hashCode() : 0);
			_result = 31 * _result + (recordStartDate != null ? recordStartDate.hashCode() : 0);
			_result = 31 * _result + (recordEndDate != null ? recordEndDate.hashCode() : 0);
			_result = 31 * _result + (minimumFee != null ? minimumFee.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BillingRecord {" +
				"tradeState=" + this.tradeState + ", " +
				"recordTransfer=" + this.recordTransfer + ", " +
				"recordStartDate=" + this.recordStartDate + ", " +
				"recordEndDate=" + this.recordEndDate + ", " +
				"minimumFee=" + this.minimumFee +
			'}';
		}
	}

	/*********************** Builder Implementation of BillingRecord  ***********************/
	class BillingRecordBuilderImpl implements BillingRecord.BillingRecordBuilder {
	
		protected ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder tradeState;
		protected Transfer.TransferBuilder recordTransfer;
		protected Date recordStartDate;
		protected Date recordEndDate;
		protected Money.MoneyBuilder minimumFee;
	
		public BillingRecordBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("tradeState")
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getTradeState() {
			return tradeState;
		}
		
		@Override
		public ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder getOrCreateTradeState() {
			ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder result;
			if (tradeState!=null) {
				result = tradeState;
			}
			else {
				result = tradeState = ReferenceWithMetaTradeState.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("recordTransfer")
		public Transfer.TransferBuilder getRecordTransfer() {
			return recordTransfer;
		}
		
		@Override
		public Transfer.TransferBuilder getOrCreateRecordTransfer() {
			Transfer.TransferBuilder result;
			if (recordTransfer!=null) {
				result = recordTransfer;
			}
			else {
				result = recordTransfer = Transfer.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("recordStartDate")
		public Date getRecordStartDate() {
			return recordStartDate;
		}
		
		@Override
		@RosettaAttribute("recordEndDate")
		public Date getRecordEndDate() {
			return recordEndDate;
		}
		
		@Override
		@RosettaAttribute("minimumFee")
		public Money.MoneyBuilder getMinimumFee() {
			return minimumFee;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateMinimumFee() {
			Money.MoneyBuilder result;
			if (minimumFee!=null) {
				result = minimumFee;
			}
			else {
				result = minimumFee = Money.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("tradeState")
		public BillingRecord.BillingRecordBuilder setTradeState(ReferenceWithMetaTradeState tradeState) {
			this.tradeState = tradeState==null?null:tradeState.toBuilder();
			return this;
		}
		@Override
		public BillingRecord.BillingRecordBuilder setTradeStateValue(TradeState tradeState) {
			this.getOrCreateTradeState().setValue(tradeState);
			return this;
		}
		@Override
		@RosettaAttribute("recordTransfer")
		public BillingRecord.BillingRecordBuilder setRecordTransfer(Transfer recordTransfer) {
			this.recordTransfer = recordTransfer==null?null:recordTransfer.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("recordStartDate")
		public BillingRecord.BillingRecordBuilder setRecordStartDate(Date recordStartDate) {
			this.recordStartDate = recordStartDate==null?null:recordStartDate;
			return this;
		}
		@Override
		@RosettaAttribute("recordEndDate")
		public BillingRecord.BillingRecordBuilder setRecordEndDate(Date recordEndDate) {
			this.recordEndDate = recordEndDate==null?null:recordEndDate;
			return this;
		}
		@Override
		@RosettaAttribute("minimumFee")
		public BillingRecord.BillingRecordBuilder setMinimumFee(Money minimumFee) {
			this.minimumFee = minimumFee==null?null:minimumFee.toBuilder();
			return this;
		}
		
		@Override
		public BillingRecord build() {
			return new BillingRecord.BillingRecordImpl(this);
		}
		
		@Override
		public BillingRecord.BillingRecordBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BillingRecord.BillingRecordBuilder prune() {
			if (tradeState!=null && !tradeState.prune().hasData()) tradeState = null;
			if (recordTransfer!=null && !recordTransfer.prune().hasData()) recordTransfer = null;
			if (minimumFee!=null && !minimumFee.prune().hasData()) minimumFee = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTradeState()!=null && getTradeState().hasData()) return true;
			if (getRecordTransfer()!=null && getRecordTransfer().hasData()) return true;
			if (getRecordStartDate()!=null) return true;
			if (getRecordEndDate()!=null) return true;
			if (getMinimumFee()!=null && getMinimumFee().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BillingRecord.BillingRecordBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BillingRecord.BillingRecordBuilder o = (BillingRecord.BillingRecordBuilder) other;
			
			merger.mergeRosetta(getTradeState(), o.getTradeState(), this::setTradeState);
			merger.mergeRosetta(getRecordTransfer(), o.getRecordTransfer(), this::setRecordTransfer);
			merger.mergeRosetta(getMinimumFee(), o.getMinimumFee(), this::setMinimumFee);
			
			merger.mergeBasic(getRecordStartDate(), o.getRecordStartDate(), this::setRecordStartDate);
			merger.mergeBasic(getRecordEndDate(), o.getRecordEndDate(), this::setRecordEndDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BillingRecord _that = getType().cast(o);
		
			if (!Objects.equals(tradeState, _that.getTradeState())) return false;
			if (!Objects.equals(recordTransfer, _that.getRecordTransfer())) return false;
			if (!Objects.equals(recordStartDate, _that.getRecordStartDate())) return false;
			if (!Objects.equals(recordEndDate, _that.getRecordEndDate())) return false;
			if (!Objects.equals(minimumFee, _that.getMinimumFee())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradeState != null ? tradeState.hashCode() : 0);
			_result = 31 * _result + (recordTransfer != null ? recordTransfer.hashCode() : 0);
			_result = 31 * _result + (recordStartDate != null ? recordStartDate.hashCode() : 0);
			_result = 31 * _result + (recordEndDate != null ? recordEndDate.hashCode() : 0);
			_result = 31 * _result + (minimumFee != null ? minimumFee.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BillingRecordBuilder {" +
				"tradeState=" + this.tradeState + ", " +
				"recordTransfer=" + this.recordTransfer + ", " +
				"recordStartDate=" + this.recordStartDate + ", " +
				"recordEndDate=" + this.recordEndDate + ", " +
				"minimumFee=" + this.minimumFee +
			'}';
		}
	}
}
