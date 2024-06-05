package cdm.product.common.settlement;

import cdm.product.common.settlement.SettlementBase;
import cdm.product.common.settlement.SettlementBase.SettlementBaseBuilder;
import cdm.product.common.settlement.SettlementBase.SettlementBaseBuilderImpl;
import cdm.product.common.settlement.SettlementBase.SettlementBaseImpl;
import cdm.product.common.settlement.SettlementCentreEnum;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementProvision;
import cdm.product.common.settlement.SettlementTypeEnum;
import cdm.product.common.settlement.StandardSettlementStyleEnum;
import cdm.product.common.settlement.TransferSettlementEnum;
import cdm.product.common.settlement.meta.SettlementBaseMeta;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A base class to be extended by the SettlementTerms class.
 * @version ${project.version}
 */
@RosettaDataType(value="SettlementBase", builder=SettlementBase.SettlementBaseBuilderImpl.class, version="${project.version}")
public interface SettlementBase extends RosettaModelObject, GlobalKey {

	SettlementBaseMeta metaData = new SettlementBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Whether the settlement will be cash, physical, by election, ...
	 */
	SettlementTypeEnum getSettlementType();
	/**
	 * The qualification as to how the transfer will settle, e.g. a DvP settlement.
	 */
	TransferSettlementEnum getTransferSettlementType();
	/**
	 * The settlement currency is to be specified when the Settlement Amount cannot be known in advance. The list of valid currencies is not presently positioned as an enumeration as part of the CDM because that scope is limited to the values specified by ISDA and FpML. As a result, implementers have to make reference to the relevant standard, such as the ISO 4217 standard for currency codes.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "Contractual Currency"
	 *
	 * Provision As defined in GMRA paragraph 2(k)/ paragraph 7(a) All the payments made in respect of the Purchase Price or the Repurchase Price of any Transaction shall be made in the currency of the Purchase Price (the Contractual Currency) save as provided in paragraph 10(d)(ii). Notwithstanding the foregoing, the payee of any money may, at its option, accept tender thereof in any other currency, provided, however, that, to the extent permitted by applicable law, the obligation of the payer to pay such money will be discharged only to the extent of the amount of the Contractual Currency that such payee may, consistent with normal banking procedures, purchase with such other currency (after deduction of any premium and costs of exchange) for delivery within the customary delivery period for spot transactions in respect of the relevant currency.
	 *
	 */
	FieldWithMetaString getSettlementCurrency();
	/**
	 * The date on which the settlement amount will be paid, subject to adjustment in accordance with any applicable business day convention. This component would not be present for a mandatory early termination provision where the cash settlement payment date is the mandatory early termination date.
	 */
	SettlementDate getSettlementDate();
	/**
	 * Optional settlement centre as an enumerated list: Euroclear, Clearstream.
	 */
	SettlementCentreEnum getSettlementCentre();
	/**
	 * Optionally defines the parameters that regulate a settlement.
	 */
	SettlementProvision getSettlementProvision();
	/**
	 * Settlement Style.
	 */
	StandardSettlementStyleEnum getStandardSettlementStyle();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	SettlementBase build();
	
	SettlementBase.SettlementBaseBuilder toBuilder();
	
	static SettlementBase.SettlementBaseBuilder builder() {
		return new SettlementBase.SettlementBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SettlementBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SettlementBase> getType() {
		return SettlementBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("settlementType"), SettlementTypeEnum.class, getSettlementType(), this);
		processor.processBasic(path.newSubPath("transferSettlementType"), TransferSettlementEnum.class, getTransferSettlementType(), this);
		processRosetta(path.newSubPath("settlementCurrency"), processor, FieldWithMetaString.class, getSettlementCurrency());
		processRosetta(path.newSubPath("settlementDate"), processor, SettlementDate.class, getSettlementDate());
		processor.processBasic(path.newSubPath("settlementCentre"), SettlementCentreEnum.class, getSettlementCentre(), this);
		processRosetta(path.newSubPath("settlementProvision"), processor, SettlementProvision.class, getSettlementProvision());
		processor.processBasic(path.newSubPath("standardSettlementStyle"), StandardSettlementStyleEnum.class, getStandardSettlementStyle(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SettlementBaseBuilder extends SettlementBase, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSettlementCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getSettlementCurrency();
		SettlementDate.SettlementDateBuilder getOrCreateSettlementDate();
		SettlementDate.SettlementDateBuilder getSettlementDate();
		SettlementProvision.SettlementProvisionBuilder getOrCreateSettlementProvision();
		SettlementProvision.SettlementProvisionBuilder getSettlementProvision();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		SettlementBase.SettlementBaseBuilder setSettlementType(SettlementTypeEnum settlementType);
		SettlementBase.SettlementBaseBuilder setTransferSettlementType(TransferSettlementEnum transferSettlementType);
		SettlementBase.SettlementBaseBuilder setSettlementCurrency(FieldWithMetaString settlementCurrency0);
		SettlementBase.SettlementBaseBuilder setSettlementCurrencyValue(String settlementCurrency1);
		SettlementBase.SettlementBaseBuilder setSettlementDate(SettlementDate settlementDate);
		SettlementBase.SettlementBaseBuilder setSettlementCentre(SettlementCentreEnum settlementCentre);
		SettlementBase.SettlementBaseBuilder setSettlementProvision(SettlementProvision settlementProvision);
		SettlementBase.SettlementBaseBuilder setStandardSettlementStyle(StandardSettlementStyleEnum standardSettlementStyle);
		SettlementBase.SettlementBaseBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("settlementType"), SettlementTypeEnum.class, getSettlementType(), this);
			processor.processBasic(path.newSubPath("transferSettlementType"), TransferSettlementEnum.class, getTransferSettlementType(), this);
			processRosetta(path.newSubPath("settlementCurrency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getSettlementCurrency());
			processRosetta(path.newSubPath("settlementDate"), processor, SettlementDate.SettlementDateBuilder.class, getSettlementDate());
			processor.processBasic(path.newSubPath("settlementCentre"), SettlementCentreEnum.class, getSettlementCentre(), this);
			processRosetta(path.newSubPath("settlementProvision"), processor, SettlementProvision.SettlementProvisionBuilder.class, getSettlementProvision());
			processor.processBasic(path.newSubPath("standardSettlementStyle"), StandardSettlementStyleEnum.class, getStandardSettlementStyle(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		SettlementBase.SettlementBaseBuilder prune();
	}

	/*********************** Immutable Implementation of SettlementBase  ***********************/
	class SettlementBaseImpl implements SettlementBase {
		private final SettlementTypeEnum settlementType;
		private final TransferSettlementEnum transferSettlementType;
		private final FieldWithMetaString settlementCurrency;
		private final SettlementDate settlementDate;
		private final SettlementCentreEnum settlementCentre;
		private final SettlementProvision settlementProvision;
		private final StandardSettlementStyleEnum standardSettlementStyle;
		private final MetaFields meta;
		
		protected SettlementBaseImpl(SettlementBase.SettlementBaseBuilder builder) {
			this.settlementType = builder.getSettlementType();
			this.transferSettlementType = builder.getTransferSettlementType();
			this.settlementCurrency = ofNullable(builder.getSettlementCurrency()).map(f->f.build()).orElse(null);
			this.settlementDate = ofNullable(builder.getSettlementDate()).map(f->f.build()).orElse(null);
			this.settlementCentre = builder.getSettlementCentre();
			this.settlementProvision = ofNullable(builder.getSettlementProvision()).map(f->f.build()).orElse(null);
			this.standardSettlementStyle = builder.getStandardSettlementStyle();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("settlementType")
		public SettlementTypeEnum getSettlementType() {
			return settlementType;
		}
		
		@Override
		@RosettaAttribute("transferSettlementType")
		public TransferSettlementEnum getTransferSettlementType() {
			return transferSettlementType;
		}
		
		@Override
		@RosettaAttribute("settlementCurrency")
		public FieldWithMetaString getSettlementCurrency() {
			return settlementCurrency;
		}
		
		@Override
		@RosettaAttribute("settlementDate")
		public SettlementDate getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		@RosettaAttribute("settlementCentre")
		public SettlementCentreEnum getSettlementCentre() {
			return settlementCentre;
		}
		
		@Override
		@RosettaAttribute("settlementProvision")
		public SettlementProvision getSettlementProvision() {
			return settlementProvision;
		}
		
		@Override
		@RosettaAttribute("standardSettlementStyle")
		public StandardSettlementStyleEnum getStandardSettlementStyle() {
			return standardSettlementStyle;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public SettlementBase build() {
			return this;
		}
		
		@Override
		public SettlementBase.SettlementBaseBuilder toBuilder() {
			SettlementBase.SettlementBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SettlementBase.SettlementBaseBuilder builder) {
			ofNullable(getSettlementType()).ifPresent(builder::setSettlementType);
			ofNullable(getTransferSettlementType()).ifPresent(builder::setTransferSettlementType);
			ofNullable(getSettlementCurrency()).ifPresent(builder::setSettlementCurrency);
			ofNullable(getSettlementDate()).ifPresent(builder::setSettlementDate);
			ofNullable(getSettlementCentre()).ifPresent(builder::setSettlementCentre);
			ofNullable(getSettlementProvision()).ifPresent(builder::setSettlementProvision);
			ofNullable(getStandardSettlementStyle()).ifPresent(builder::setStandardSettlementStyle);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementBase _that = getType().cast(o);
		
			if (!Objects.equals(settlementType, _that.getSettlementType())) return false;
			if (!Objects.equals(transferSettlementType, _that.getTransferSettlementType())) return false;
			if (!Objects.equals(settlementCurrency, _that.getSettlementCurrency())) return false;
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			if (!Objects.equals(settlementCentre, _that.getSettlementCentre())) return false;
			if (!Objects.equals(settlementProvision, _that.getSettlementProvision())) return false;
			if (!Objects.equals(standardSettlementStyle, _that.getStandardSettlementStyle())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementType != null ? settlementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (transferSettlementType != null ? transferSettlementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (settlementCurrency != null ? settlementCurrency.hashCode() : 0);
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			_result = 31 * _result + (settlementCentre != null ? settlementCentre.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (settlementProvision != null ? settlementProvision.hashCode() : 0);
			_result = 31 * _result + (standardSettlementStyle != null ? standardSettlementStyle.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementBase {" +
				"settlementType=" + this.settlementType + ", " +
				"transferSettlementType=" + this.transferSettlementType + ", " +
				"settlementCurrency=" + this.settlementCurrency + ", " +
				"settlementDate=" + this.settlementDate + ", " +
				"settlementCentre=" + this.settlementCentre + ", " +
				"settlementProvision=" + this.settlementProvision + ", " +
				"standardSettlementStyle=" + this.standardSettlementStyle + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of SettlementBase  ***********************/
	class SettlementBaseBuilderImpl implements SettlementBase.SettlementBaseBuilder, GlobalKeyBuilder {
	
		protected SettlementTypeEnum settlementType;
		protected TransferSettlementEnum transferSettlementType;
		protected FieldWithMetaString.FieldWithMetaStringBuilder settlementCurrency;
		protected SettlementDate.SettlementDateBuilder settlementDate;
		protected SettlementCentreEnum settlementCentre;
		protected SettlementProvision.SettlementProvisionBuilder settlementProvision;
		protected StandardSettlementStyleEnum standardSettlementStyle;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public SettlementBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("settlementType")
		public SettlementTypeEnum getSettlementType() {
			return settlementType;
		}
		
		@Override
		@RosettaAttribute("transferSettlementType")
		public TransferSettlementEnum getTransferSettlementType() {
			return transferSettlementType;
		}
		
		@Override
		@RosettaAttribute("settlementCurrency")
		public FieldWithMetaString.FieldWithMetaStringBuilder getSettlementCurrency() {
			return settlementCurrency;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSettlementCurrency() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (settlementCurrency!=null) {
				result = settlementCurrency;
			}
			else {
				result = settlementCurrency = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public SettlementDate.SettlementDateBuilder getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		public SettlementDate.SettlementDateBuilder getOrCreateSettlementDate() {
			SettlementDate.SettlementDateBuilder result;
			if (settlementDate!=null) {
				result = settlementDate;
			}
			else {
				result = settlementDate = SettlementDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settlementCentre")
		public SettlementCentreEnum getSettlementCentre() {
			return settlementCentre;
		}
		
		@Override
		@RosettaAttribute("settlementProvision")
		public SettlementProvision.SettlementProvisionBuilder getSettlementProvision() {
			return settlementProvision;
		}
		
		@Override
		public SettlementProvision.SettlementProvisionBuilder getOrCreateSettlementProvision() {
			SettlementProvision.SettlementProvisionBuilder result;
			if (settlementProvision!=null) {
				result = settlementProvision;
			}
			else {
				result = settlementProvision = SettlementProvision.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("standardSettlementStyle")
		public StandardSettlementStyleEnum getStandardSettlementStyle() {
			return standardSettlementStyle;
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
		@RosettaAttribute("settlementType")
		public SettlementBase.SettlementBaseBuilder setSettlementType(SettlementTypeEnum settlementType) {
			this.settlementType = settlementType==null?null:settlementType;
			return this;
		}
		@Override
		@RosettaAttribute("transferSettlementType")
		public SettlementBase.SettlementBaseBuilder setTransferSettlementType(TransferSettlementEnum transferSettlementType) {
			this.transferSettlementType = transferSettlementType==null?null:transferSettlementType;
			return this;
		}
		@Override
		@RosettaAttribute("settlementCurrency")
		public SettlementBase.SettlementBaseBuilder setSettlementCurrency(FieldWithMetaString settlementCurrency) {
			this.settlementCurrency = settlementCurrency==null?null:settlementCurrency.toBuilder();
			return this;
		}
		@Override
		public SettlementBase.SettlementBaseBuilder setSettlementCurrencyValue(String settlementCurrency) {
			this.getOrCreateSettlementCurrency().setValue(settlementCurrency);
			return this;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public SettlementBase.SettlementBaseBuilder setSettlementDate(SettlementDate settlementDate) {
			this.settlementDate = settlementDate==null?null:settlementDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementCentre")
		public SettlementBase.SettlementBaseBuilder setSettlementCentre(SettlementCentreEnum settlementCentre) {
			this.settlementCentre = settlementCentre==null?null:settlementCentre;
			return this;
		}
		@Override
		@RosettaAttribute("settlementProvision")
		public SettlementBase.SettlementBaseBuilder setSettlementProvision(SettlementProvision settlementProvision) {
			this.settlementProvision = settlementProvision==null?null:settlementProvision.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("standardSettlementStyle")
		public SettlementBase.SettlementBaseBuilder setStandardSettlementStyle(StandardSettlementStyleEnum standardSettlementStyle) {
			this.standardSettlementStyle = standardSettlementStyle==null?null:standardSettlementStyle;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public SettlementBase.SettlementBaseBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public SettlementBase build() {
			return new SettlementBase.SettlementBaseImpl(this);
		}
		
		@Override
		public SettlementBase.SettlementBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementBase.SettlementBaseBuilder prune() {
			if (settlementCurrency!=null && !settlementCurrency.prune().hasData()) settlementCurrency = null;
			if (settlementDate!=null && !settlementDate.prune().hasData()) settlementDate = null;
			if (settlementProvision!=null && !settlementProvision.prune().hasData()) settlementProvision = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSettlementType()!=null) return true;
			if (getTransferSettlementType()!=null) return true;
			if (getSettlementCurrency()!=null) return true;
			if (getSettlementDate()!=null && getSettlementDate().hasData()) return true;
			if (getSettlementCentre()!=null) return true;
			if (getSettlementProvision()!=null && getSettlementProvision().hasData()) return true;
			if (getStandardSettlementStyle()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementBase.SettlementBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SettlementBase.SettlementBaseBuilder o = (SettlementBase.SettlementBaseBuilder) other;
			
			merger.mergeRosetta(getSettlementCurrency(), o.getSettlementCurrency(), this::setSettlementCurrency);
			merger.mergeRosetta(getSettlementDate(), o.getSettlementDate(), this::setSettlementDate);
			merger.mergeRosetta(getSettlementProvision(), o.getSettlementProvision(), this::setSettlementProvision);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getSettlementType(), o.getSettlementType(), this::setSettlementType);
			merger.mergeBasic(getTransferSettlementType(), o.getTransferSettlementType(), this::setTransferSettlementType);
			merger.mergeBasic(getSettlementCentre(), o.getSettlementCentre(), this::setSettlementCentre);
			merger.mergeBasic(getStandardSettlementStyle(), o.getStandardSettlementStyle(), this::setStandardSettlementStyle);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SettlementBase _that = getType().cast(o);
		
			if (!Objects.equals(settlementType, _that.getSettlementType())) return false;
			if (!Objects.equals(transferSettlementType, _that.getTransferSettlementType())) return false;
			if (!Objects.equals(settlementCurrency, _that.getSettlementCurrency())) return false;
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			if (!Objects.equals(settlementCentre, _that.getSettlementCentre())) return false;
			if (!Objects.equals(settlementProvision, _that.getSettlementProvision())) return false;
			if (!Objects.equals(standardSettlementStyle, _that.getStandardSettlementStyle())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementType != null ? settlementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (transferSettlementType != null ? transferSettlementType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (settlementCurrency != null ? settlementCurrency.hashCode() : 0);
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			_result = 31 * _result + (settlementCentre != null ? settlementCentre.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (settlementProvision != null ? settlementProvision.hashCode() : 0);
			_result = 31 * _result + (standardSettlementStyle != null ? standardSettlementStyle.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementBaseBuilder {" +
				"settlementType=" + this.settlementType + ", " +
				"transferSettlementType=" + this.transferSettlementType + ", " +
				"settlementCurrency=" + this.settlementCurrency + ", " +
				"settlementDate=" + this.settlementDate + ", " +
				"settlementCentre=" + this.settlementCentre + ", " +
				"settlementProvision=" + this.settlementProvision + ", " +
				"standardSettlementStyle=" + this.standardSettlementStyle + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
