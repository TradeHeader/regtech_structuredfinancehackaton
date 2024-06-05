package cdm.product.common.settlement;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.BusinessDateRange;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementDate.SettlementDateBuilder;
import cdm.product.common.settlement.SettlementDate.SettlementDateBuilderImpl;
import cdm.product.common.settlement.SettlementDate.SettlementDateImpl;
import cdm.product.common.settlement.meta.SettlementDateMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
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
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A data defining the settlement date(s) for cash or physical settlement as either a set of explicit dates, together with applicable adjustments, or as a date relative to some other (anchor) date, or as any date in a range of contiguous business days. This data type provides a level of abstraction on top of the different legacy methods used to specify a settlement / payment date, which vary across product types, asset classes and delivery types.
 * @version ${project.version}
 */
@RosettaDataType(value="SettlementDate", builder=SettlementDate.SettlementDateBuilderImpl.class, version="${project.version}")
public interface SettlementDate extends RosettaModelObject, GlobalKey {

	SettlementDateMeta metaData = new SettlementDateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A single settlement date subject to adjustment or specified as relative to another date (e.g. the trade date). This attribute was formerly part of &#39;SettlementTerms&#39;, which is now being harmonised to include a common &#39;SettlementDate&#39;, as inherited from &#39;SettlementBase&#39;.
	 */
	AdjustableOrAdjustedOrRelativeDate getAdjustableOrRelativeDate();
	/**
	 * The settlement date for a forward settling product. For Foreign Exchange contracts, this represents a common settlement date between both currency legs. To specify different settlement dates for each currency leg, see the ForeignExchange class. This attribute was formerly part of &#39;SettlementTerms&#39;, which is now being harmonised to include a common &#39;SettlementDate&#39;, as inherited from &#39;SettlementBase&#39;.
	 */
	Date getValueDate();
	/**
	 * A series of dates that shall be subject to adjustment if they would otherwise fall on a day that is not a business day in the specified business centers, together with the convention for adjusting the date. This attributes was formerly part of &#39;CashSettlementPaymentDate&#39; as included into &#39;OptionCashSettlement&#39; (which is now merged into a unique &#39;CashSettlementTerms&#39; data type.
	 */
	AdjustableDates getAdjustableDates();
	/**
	 * A range of contiguous business days. This attribute is meant to be merged with the &#39;settlementDate&#39; at some future point once we refactor &#39;Date&#39; to use a single complex type across the model. This attributes was formerly part of &#39;CashSettlementPaymentDate&#39;, as included into &#39;OptionCashSettlement&#39; (which is now merged into a unique &#39;CashSettlementTerms&#39; data type.
	 */
	BusinessDateRange getBusinessDateRange();
	/**
	 * The number of business days used in the determination of the cash settlement payment date. If a cash settlement amount is specified, the cash settlement payment date will be this number of business days following the calculation of the final price. If a cash settlement amount is not specified, the cash settlement payment date will be this number of business days after all conditions to settlement are satisfied. ISDA 2003 Term: Cash Settlement Date. This attribute was formerly part of &#39;CashSettlementTerms&#39; as used for credit event settlement, which now includes a common &#39;SettlementDate&#39; attribute.
	 */
	Integer getCashSettlementBusinessDays();
	/**
	 * Applicable to CDS on MBS to specify whether payment delays are applicable to the fixed Amount. RMBS typically have a payment delay of 5 days between the coupon date of the reference obligation and the payment date of the synthetic swap. CMBS do not, on the other hand, with both payment dates being on the 25th of each month.
	 */
	Boolean getPaymentDelay();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	SettlementDate build();
	
	SettlementDate.SettlementDateBuilder toBuilder();
	
	static SettlementDate.SettlementDateBuilder builder() {
		return new SettlementDate.SettlementDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SettlementDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SettlementDate> getType() {
		return SettlementDate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("adjustableOrRelativeDate"), processor, AdjustableOrAdjustedOrRelativeDate.class, getAdjustableOrRelativeDate());
		processor.processBasic(path.newSubPath("valueDate"), Date.class, getValueDate(), this);
		processRosetta(path.newSubPath("adjustableDates"), processor, AdjustableDates.class, getAdjustableDates());
		processRosetta(path.newSubPath("businessDateRange"), processor, BusinessDateRange.class, getBusinessDateRange());
		processor.processBasic(path.newSubPath("cashSettlementBusinessDays"), Integer.class, getCashSettlementBusinessDays(), this);
		processor.processBasic(path.newSubPath("paymentDelay"), Boolean.class, getPaymentDelay(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SettlementDateBuilder extends SettlementDate, RosettaModelObjectBuilder {
		AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder getOrCreateAdjustableOrRelativeDate();
		AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder getAdjustableOrRelativeDate();
		AdjustableDates.AdjustableDatesBuilder getOrCreateAdjustableDates();
		AdjustableDates.AdjustableDatesBuilder getAdjustableDates();
		BusinessDateRange.BusinessDateRangeBuilder getOrCreateBusinessDateRange();
		BusinessDateRange.BusinessDateRangeBuilder getBusinessDateRange();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		SettlementDate.SettlementDateBuilder setAdjustableOrRelativeDate(AdjustableOrAdjustedOrRelativeDate adjustableOrRelativeDate);
		SettlementDate.SettlementDateBuilder setValueDate(Date valueDate);
		SettlementDate.SettlementDateBuilder setAdjustableDates(AdjustableDates adjustableDates);
		SettlementDate.SettlementDateBuilder setBusinessDateRange(BusinessDateRange businessDateRange);
		SettlementDate.SettlementDateBuilder setCashSettlementBusinessDays(Integer cashSettlementBusinessDays);
		SettlementDate.SettlementDateBuilder setPaymentDelay(Boolean paymentDelay);
		SettlementDate.SettlementDateBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("adjustableOrRelativeDate"), processor, AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder.class, getAdjustableOrRelativeDate());
			processor.processBasic(path.newSubPath("valueDate"), Date.class, getValueDate(), this);
			processRosetta(path.newSubPath("adjustableDates"), processor, AdjustableDates.AdjustableDatesBuilder.class, getAdjustableDates());
			processRosetta(path.newSubPath("businessDateRange"), processor, BusinessDateRange.BusinessDateRangeBuilder.class, getBusinessDateRange());
			processor.processBasic(path.newSubPath("cashSettlementBusinessDays"), Integer.class, getCashSettlementBusinessDays(), this);
			processor.processBasic(path.newSubPath("paymentDelay"), Boolean.class, getPaymentDelay(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		SettlementDate.SettlementDateBuilder prune();
	}

	/*********************** Immutable Implementation of SettlementDate  ***********************/
	class SettlementDateImpl implements SettlementDate {
		private final AdjustableOrAdjustedOrRelativeDate adjustableOrRelativeDate;
		private final Date valueDate;
		private final AdjustableDates adjustableDates;
		private final BusinessDateRange businessDateRange;
		private final Integer cashSettlementBusinessDays;
		private final Boolean paymentDelay;
		private final MetaFields meta;
		
		protected SettlementDateImpl(SettlementDate.SettlementDateBuilder builder) {
			this.adjustableOrRelativeDate = ofNullable(builder.getAdjustableOrRelativeDate()).map(f->f.build()).orElse(null);
			this.valueDate = builder.getValueDate();
			this.adjustableDates = ofNullable(builder.getAdjustableDates()).map(f->f.build()).orElse(null);
			this.businessDateRange = ofNullable(builder.getBusinessDateRange()).map(f->f.build()).orElse(null);
			this.cashSettlementBusinessDays = builder.getCashSettlementBusinessDays();
			this.paymentDelay = builder.getPaymentDelay();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("adjustableOrRelativeDate")
		public AdjustableOrAdjustedOrRelativeDate getAdjustableOrRelativeDate() {
			return adjustableOrRelativeDate;
		}
		
		@Override
		@RosettaAttribute("valueDate")
		public Date getValueDate() {
			return valueDate;
		}
		
		@Override
		@RosettaAttribute("adjustableDates")
		public AdjustableDates getAdjustableDates() {
			return adjustableDates;
		}
		
		@Override
		@RosettaAttribute("businessDateRange")
		public BusinessDateRange getBusinessDateRange() {
			return businessDateRange;
		}
		
		@Override
		@RosettaAttribute("cashSettlementBusinessDays")
		public Integer getCashSettlementBusinessDays() {
			return cashSettlementBusinessDays;
		}
		
		@Override
		@RosettaAttribute("paymentDelay")
		public Boolean getPaymentDelay() {
			return paymentDelay;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public SettlementDate build() {
			return this;
		}
		
		@Override
		public SettlementDate.SettlementDateBuilder toBuilder() {
			SettlementDate.SettlementDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SettlementDate.SettlementDateBuilder builder) {
			ofNullable(getAdjustableOrRelativeDate()).ifPresent(builder::setAdjustableOrRelativeDate);
			ofNullable(getValueDate()).ifPresent(builder::setValueDate);
			ofNullable(getAdjustableDates()).ifPresent(builder::setAdjustableDates);
			ofNullable(getBusinessDateRange()).ifPresent(builder::setBusinessDateRange);
			ofNullable(getCashSettlementBusinessDays()).ifPresent(builder::setCashSettlementBusinessDays);
			ofNullable(getPaymentDelay()).ifPresent(builder::setPaymentDelay);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementDate _that = getType().cast(o);
		
			if (!Objects.equals(adjustableOrRelativeDate, _that.getAdjustableOrRelativeDate())) return false;
			if (!Objects.equals(valueDate, _that.getValueDate())) return false;
			if (!Objects.equals(adjustableDates, _that.getAdjustableDates())) return false;
			if (!Objects.equals(businessDateRange, _that.getBusinessDateRange())) return false;
			if (!Objects.equals(cashSettlementBusinessDays, _that.getCashSettlementBusinessDays())) return false;
			if (!Objects.equals(paymentDelay, _that.getPaymentDelay())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustableOrRelativeDate != null ? adjustableOrRelativeDate.hashCode() : 0);
			_result = 31 * _result + (valueDate != null ? valueDate.hashCode() : 0);
			_result = 31 * _result + (adjustableDates != null ? adjustableDates.hashCode() : 0);
			_result = 31 * _result + (businessDateRange != null ? businessDateRange.hashCode() : 0);
			_result = 31 * _result + (cashSettlementBusinessDays != null ? cashSettlementBusinessDays.hashCode() : 0);
			_result = 31 * _result + (paymentDelay != null ? paymentDelay.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementDate {" +
				"adjustableOrRelativeDate=" + this.adjustableOrRelativeDate + ", " +
				"valueDate=" + this.valueDate + ", " +
				"adjustableDates=" + this.adjustableDates + ", " +
				"businessDateRange=" + this.businessDateRange + ", " +
				"cashSettlementBusinessDays=" + this.cashSettlementBusinessDays + ", " +
				"paymentDelay=" + this.paymentDelay + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of SettlementDate  ***********************/
	class SettlementDateBuilderImpl implements SettlementDate.SettlementDateBuilder, GlobalKeyBuilder {
	
		protected AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder adjustableOrRelativeDate;
		protected Date valueDate;
		protected AdjustableDates.AdjustableDatesBuilder adjustableDates;
		protected BusinessDateRange.BusinessDateRangeBuilder businessDateRange;
		protected Integer cashSettlementBusinessDays;
		protected Boolean paymentDelay;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public SettlementDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("adjustableOrRelativeDate")
		public AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder getAdjustableOrRelativeDate() {
			return adjustableOrRelativeDate;
		}
		
		@Override
		public AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder getOrCreateAdjustableOrRelativeDate() {
			AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder result;
			if (adjustableOrRelativeDate!=null) {
				result = adjustableOrRelativeDate;
			}
			else {
				result = adjustableOrRelativeDate = AdjustableOrAdjustedOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valueDate")
		public Date getValueDate() {
			return valueDate;
		}
		
		@Override
		@RosettaAttribute("adjustableDates")
		public AdjustableDates.AdjustableDatesBuilder getAdjustableDates() {
			return adjustableDates;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder getOrCreateAdjustableDates() {
			AdjustableDates.AdjustableDatesBuilder result;
			if (adjustableDates!=null) {
				result = adjustableDates;
			}
			else {
				result = adjustableDates = AdjustableDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("businessDateRange")
		public BusinessDateRange.BusinessDateRangeBuilder getBusinessDateRange() {
			return businessDateRange;
		}
		
		@Override
		public BusinessDateRange.BusinessDateRangeBuilder getOrCreateBusinessDateRange() {
			BusinessDateRange.BusinessDateRangeBuilder result;
			if (businessDateRange!=null) {
				result = businessDateRange;
			}
			else {
				result = businessDateRange = BusinessDateRange.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("cashSettlementBusinessDays")
		public Integer getCashSettlementBusinessDays() {
			return cashSettlementBusinessDays;
		}
		
		@Override
		@RosettaAttribute("paymentDelay")
		public Boolean getPaymentDelay() {
			return paymentDelay;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("adjustableOrRelativeDate")
		public SettlementDate.SettlementDateBuilder setAdjustableOrRelativeDate(AdjustableOrAdjustedOrRelativeDate adjustableOrRelativeDate) {
			this.adjustableOrRelativeDate = adjustableOrRelativeDate==null?null:adjustableOrRelativeDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valueDate")
		public SettlementDate.SettlementDateBuilder setValueDate(Date valueDate) {
			this.valueDate = valueDate==null?null:valueDate;
			return this;
		}
		@Override
		@RosettaAttribute("adjustableDates")
		public SettlementDate.SettlementDateBuilder setAdjustableDates(AdjustableDates adjustableDates) {
			this.adjustableDates = adjustableDates==null?null:adjustableDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("businessDateRange")
		public SettlementDate.SettlementDateBuilder setBusinessDateRange(BusinessDateRange businessDateRange) {
			this.businessDateRange = businessDateRange==null?null:businessDateRange.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("cashSettlementBusinessDays")
		public SettlementDate.SettlementDateBuilder setCashSettlementBusinessDays(Integer cashSettlementBusinessDays) {
			this.cashSettlementBusinessDays = cashSettlementBusinessDays==null?null:cashSettlementBusinessDays;
			return this;
		}
		@Override
		@RosettaAttribute("paymentDelay")
		public SettlementDate.SettlementDateBuilder setPaymentDelay(Boolean paymentDelay) {
			this.paymentDelay = paymentDelay==null?null:paymentDelay;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public SettlementDate.SettlementDateBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public SettlementDate build() {
			return new SettlementDate.SettlementDateImpl(this);
		}
		
		@Override
		public SettlementDate.SettlementDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementDate.SettlementDateBuilder prune() {
			if (adjustableOrRelativeDate!=null && !adjustableOrRelativeDate.prune().hasData()) adjustableOrRelativeDate = null;
			if (adjustableDates!=null && !adjustableDates.prune().hasData()) adjustableDates = null;
			if (businessDateRange!=null && !businessDateRange.prune().hasData()) businessDateRange = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAdjustableOrRelativeDate()!=null && getAdjustableOrRelativeDate().hasData()) return true;
			if (getValueDate()!=null) return true;
			if (getAdjustableDates()!=null && getAdjustableDates().hasData()) return true;
			if (getBusinessDateRange()!=null && getBusinessDateRange().hasData()) return true;
			if (getCashSettlementBusinessDays()!=null) return true;
			if (getPaymentDelay()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementDate.SettlementDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SettlementDate.SettlementDateBuilder o = (SettlementDate.SettlementDateBuilder) other;
			
			merger.mergeRosetta(getAdjustableOrRelativeDate(), o.getAdjustableOrRelativeDate(), this::setAdjustableOrRelativeDate);
			merger.mergeRosetta(getAdjustableDates(), o.getAdjustableDates(), this::setAdjustableDates);
			merger.mergeRosetta(getBusinessDateRange(), o.getBusinessDateRange(), this::setBusinessDateRange);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValueDate(), o.getValueDate(), this::setValueDate);
			merger.mergeBasic(getCashSettlementBusinessDays(), o.getCashSettlementBusinessDays(), this::setCashSettlementBusinessDays);
			merger.mergeBasic(getPaymentDelay(), o.getPaymentDelay(), this::setPaymentDelay);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementDate _that = getType().cast(o);
		
			if (!Objects.equals(adjustableOrRelativeDate, _that.getAdjustableOrRelativeDate())) return false;
			if (!Objects.equals(valueDate, _that.getValueDate())) return false;
			if (!Objects.equals(adjustableDates, _that.getAdjustableDates())) return false;
			if (!Objects.equals(businessDateRange, _that.getBusinessDateRange())) return false;
			if (!Objects.equals(cashSettlementBusinessDays, _that.getCashSettlementBusinessDays())) return false;
			if (!Objects.equals(paymentDelay, _that.getPaymentDelay())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustableOrRelativeDate != null ? adjustableOrRelativeDate.hashCode() : 0);
			_result = 31 * _result + (valueDate != null ? valueDate.hashCode() : 0);
			_result = 31 * _result + (adjustableDates != null ? adjustableDates.hashCode() : 0);
			_result = 31 * _result + (businessDateRange != null ? businessDateRange.hashCode() : 0);
			_result = 31 * _result + (cashSettlementBusinessDays != null ? cashSettlementBusinessDays.hashCode() : 0);
			_result = 31 * _result + (paymentDelay != null ? paymentDelay.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementDateBuilder {" +
				"adjustableOrRelativeDate=" + this.adjustableOrRelativeDate + ", " +
				"valueDate=" + this.valueDate + ", " +
				"adjustableDates=" + this.adjustableDates + ", " +
				"businessDateRange=" + this.businessDateRange + ", " +
				"cashSettlementBusinessDays=" + this.cashSettlementBusinessDays + ", " +
				"paymentDelay=" + this.paymentDelay + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
