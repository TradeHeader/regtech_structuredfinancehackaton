package cdm.product.collateral;

import cdm.product.collateral.CollateralValuationTreatment;
import cdm.product.collateral.CollateralValuationTreatment.CollateralValuationTreatmentBuilder;
import cdm.product.collateral.CollateralValuationTreatment.CollateralValuationTreatmentBuilderImpl;
import cdm.product.collateral.CollateralValuationTreatment.CollateralValuationTreatmentImpl;
import cdm.product.collateral.meta.CollateralValuationTreatmentMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specification of the valuation treatment for the specified collateral.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralValuationTreatment", builder=CollateralValuationTreatment.CollateralValuationTreatmentBuilderImpl.class, version="${project.version}")
public interface CollateralValuationTreatment extends RosettaModelObject {

	CollateralValuationTreatmentMeta metaData = new CollateralValuationTreatmentMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a haircut percentage to be applied to the value of asset and used as a discount factor to the value of the collateral asset,expressed as a percentage in decimal terms. As an example a 0.5% haircut would be represented as a decimal number 0.005.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "Haircut"
	 *
	 * Provision As defined in GMRA paragraph 2(xx)(B). The haircut for the relevant Securities, if any, as agreed by the parties from time to time, being a discount from the Market Value of the Securities.
	 *
	 *
	 * Body ICMA
	 * Corpus Guidance ERCCBestPractice ERCC Guide to Best Practice in the European Repo Market ERCC Guide to Best Practice in the European Repo Market "The ERCC Guide to Best Practice in the European Repo Market is published by ICMAs European Repo and Collateral Council (ERCC). Its purpose is to help foster a fair and efficient European repo market by recommending practices which market experience suggests can help avoid uncertainty or disagreement about transactions, and consequent delay or disruption to repo trading and settlement. With the same purpose in mind, the Guide also codifies market conventions, where this has been thought to be helpful, usually in response to queries from market participants." 
	 * namingConvention "Haircut"
	 *
	 * Provision ERCC Guide 3.1: Initial margins and Haircuts are alternative ways to risk-adjust the value of collateral sold in a repurchase transaction in order to try to anticipate the loss of value that may be experienced if the collateral has to be liquidated following an event of default by the counterparty. Both amounts are therefore used to fix the expected liquidation value of collateral. Annex II  Glossary of repo terminology Haircut: An agreed percentage discount applied to the Market Value of collateral to fix the Purchase Price on the Purchase Date of a repo. A haircut is expressed as the percentage difference between the initial Market Value and the Purchase Price. 
	 *
	 */
	BigDecimal getHaircutPercentage();
	/**
	 * Specifies a percentage value of transaction needing to be posted as collateral expressed as a valuation. As an example a 104% requirement would be represented as a decimal number 1.04.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "Margin Ratio"
	 *
	 * Provision As defined in GMRA paragraph 2(bb). Margin Ratio, with respect to a Transaction, the Market Value of the Purchased Securities at the time when the Transaction was entered into divided by the Purchase Price (and so that, where a Transaction relates to Securities of different descriptions and the Purchase Price is apportioned by the parties among Purchased Securities of each such description, a separate Margin Ratio shall apply in respect of Securities of each such description), or such other proportion as the parties may agree with respect to that Transaction;
	 *
	 *
	 * Body ICMA
	 * Corpus Guidance ERCCBestPractice ERCC Guide to Best Practice in the European Repo Market ERCC Guide to Best Practice in the European Repo Market "The ERCC Guide to Best Practice in the European Repo Market is published by ICMAs European Repo and Collateral Council (ERCC). Its purpose is to help foster a fair and efficient European repo market by recommending practices which market experience suggests can help avoid uncertainty or disagreement about transactions, and consequent delay or disruption to repo trading and settlement. With the same purpose in mind, the Guide also codifies market conventions, where this has been thought to be helpful, usually in response to queries from market participants." 
	 * namingConvention "Margin Ratio"
	 *
	 * Provision ERCC Guide 3.1: Initial margins and Haircuts are alternative ways to risk-adjust the value of collateral sold in a repurchase transaction in order to try to anticipate the loss of value that may be experienced if the collateral has to be liquidated following an event of default by the counterparty. Both amounts are therefore used to fix the expected liquidation value of collateral. Annex II  Glossary of repo terminology: Initial margin: An agreed premium applied to the Purchase Price of a repo to determine the required Market Value of the collateral to be delivered on the Purchase Date. It is also applied each day during the term of a repo, as part of the process of Margin Maintenance, to the Repurchase Price on that day to calculate the Market Value of collateral required subsequently in order to maintain adequate collateralisation. Under the GMRA, if there is a material difference between (1) the Repurchase Price of a repo plus any initial margin and (2) the current Market Value of collateral, that repo has a Transaction Exposure. This will go into the calculation of Net Exposure, which determines if either party has the right to call for Margin Maintenance. An initial margin can be expressed either as (1) the Market Value as a percentage of the Purchase Price or (2) a ratio of the two amounts. In the GMRA, an initial margin is called a Margin Ratio and is defined as a ratio but the market tends to quote a percentage. A percentage initial margin of 100% or ratio of one means there is no initial margin. See Guide 3.2
	 *
	 */
	BigDecimal getMarginPercentage();
	/**
	 * Specifies an FX haircut applied to a specific asset which is agreed between the parties (for example, if pledgor eligible collateral is not denominated in the termination currency or under other specified cases in collateral support documents both for initial margin and variation margin).The percentage value is expressed as the discount haircut to the value of the collateral- as an example an 8% FX haircut would be expressed as 0.08.
	 */
	BigDecimal getFxHaircutPercentage();
	/**
	 * Specifies a percentage value of any additional haircut to be applied to a collateral asset,the percentage value is expressed as the discount haircut to the value of the collateral- as an example a 5% haircut would be expressed as 0.05. 
	 */
	BigDecimal getAdditionalHaircutPercentage();

	/*********************** Build Methods  ***********************/
	CollateralValuationTreatment build();
	
	CollateralValuationTreatment.CollateralValuationTreatmentBuilder toBuilder();
	
	static CollateralValuationTreatment.CollateralValuationTreatmentBuilder builder() {
		return new CollateralValuationTreatment.CollateralValuationTreatmentBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralValuationTreatment> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralValuationTreatment> getType() {
		return CollateralValuationTreatment.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("haircutPercentage"), BigDecimal.class, getHaircutPercentage(), this);
		processor.processBasic(path.newSubPath("marginPercentage"), BigDecimal.class, getMarginPercentage(), this);
		processor.processBasic(path.newSubPath("fxHaircutPercentage"), BigDecimal.class, getFxHaircutPercentage(), this);
		processor.processBasic(path.newSubPath("additionalHaircutPercentage"), BigDecimal.class, getAdditionalHaircutPercentage(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralValuationTreatmentBuilder extends CollateralValuationTreatment, RosettaModelObjectBuilder {
		CollateralValuationTreatment.CollateralValuationTreatmentBuilder setHaircutPercentage(BigDecimal haircutPercentage);
		CollateralValuationTreatment.CollateralValuationTreatmentBuilder setMarginPercentage(BigDecimal marginPercentage);
		CollateralValuationTreatment.CollateralValuationTreatmentBuilder setFxHaircutPercentage(BigDecimal fxHaircutPercentage);
		CollateralValuationTreatment.CollateralValuationTreatmentBuilder setAdditionalHaircutPercentage(BigDecimal additionalHaircutPercentage);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("haircutPercentage"), BigDecimal.class, getHaircutPercentage(), this);
			processor.processBasic(path.newSubPath("marginPercentage"), BigDecimal.class, getMarginPercentage(), this);
			processor.processBasic(path.newSubPath("fxHaircutPercentage"), BigDecimal.class, getFxHaircutPercentage(), this);
			processor.processBasic(path.newSubPath("additionalHaircutPercentage"), BigDecimal.class, getAdditionalHaircutPercentage(), this);
		}
		

		CollateralValuationTreatment.CollateralValuationTreatmentBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralValuationTreatment  ***********************/
	class CollateralValuationTreatmentImpl implements CollateralValuationTreatment {
		private final BigDecimal haircutPercentage;
		private final BigDecimal marginPercentage;
		private final BigDecimal fxHaircutPercentage;
		private final BigDecimal additionalHaircutPercentage;
		
		protected CollateralValuationTreatmentImpl(CollateralValuationTreatment.CollateralValuationTreatmentBuilder builder) {
			this.haircutPercentage = builder.getHaircutPercentage();
			this.marginPercentage = builder.getMarginPercentage();
			this.fxHaircutPercentage = builder.getFxHaircutPercentage();
			this.additionalHaircutPercentage = builder.getAdditionalHaircutPercentage();
		}
		
		@Override
		@RosettaAttribute("haircutPercentage")
		public BigDecimal getHaircutPercentage() {
			return haircutPercentage;
		}
		
		@Override
		@RosettaAttribute("marginPercentage")
		public BigDecimal getMarginPercentage() {
			return marginPercentage;
		}
		
		@Override
		@RosettaAttribute("fxHaircutPercentage")
		public BigDecimal getFxHaircutPercentage() {
			return fxHaircutPercentage;
		}
		
		@Override
		@RosettaAttribute("additionalHaircutPercentage")
		public BigDecimal getAdditionalHaircutPercentage() {
			return additionalHaircutPercentage;
		}
		
		@Override
		public CollateralValuationTreatment build() {
			return this;
		}
		
		@Override
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder toBuilder() {
			CollateralValuationTreatment.CollateralValuationTreatmentBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralValuationTreatment.CollateralValuationTreatmentBuilder builder) {
			ofNullable(getHaircutPercentage()).ifPresent(builder::setHaircutPercentage);
			ofNullable(getMarginPercentage()).ifPresent(builder::setMarginPercentage);
			ofNullable(getFxHaircutPercentage()).ifPresent(builder::setFxHaircutPercentage);
			ofNullable(getAdditionalHaircutPercentage()).ifPresent(builder::setAdditionalHaircutPercentage);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralValuationTreatment _that = getType().cast(o);
		
			if (!Objects.equals(haircutPercentage, _that.getHaircutPercentage())) return false;
			if (!Objects.equals(marginPercentage, _that.getMarginPercentage())) return false;
			if (!Objects.equals(fxHaircutPercentage, _that.getFxHaircutPercentage())) return false;
			if (!Objects.equals(additionalHaircutPercentage, _that.getAdditionalHaircutPercentage())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (haircutPercentage != null ? haircutPercentage.hashCode() : 0);
			_result = 31 * _result + (marginPercentage != null ? marginPercentage.hashCode() : 0);
			_result = 31 * _result + (fxHaircutPercentage != null ? fxHaircutPercentage.hashCode() : 0);
			_result = 31 * _result + (additionalHaircutPercentage != null ? additionalHaircutPercentage.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralValuationTreatment {" +
				"haircutPercentage=" + this.haircutPercentage + ", " +
				"marginPercentage=" + this.marginPercentage + ", " +
				"fxHaircutPercentage=" + this.fxHaircutPercentage + ", " +
				"additionalHaircutPercentage=" + this.additionalHaircutPercentage +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralValuationTreatment  ***********************/
	class CollateralValuationTreatmentBuilderImpl implements CollateralValuationTreatment.CollateralValuationTreatmentBuilder {
	
		protected BigDecimal haircutPercentage;
		protected BigDecimal marginPercentage;
		protected BigDecimal fxHaircutPercentage;
		protected BigDecimal additionalHaircutPercentage;
	
		public CollateralValuationTreatmentBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("haircutPercentage")
		public BigDecimal getHaircutPercentage() {
			return haircutPercentage;
		}
		
		@Override
		@RosettaAttribute("marginPercentage")
		public BigDecimal getMarginPercentage() {
			return marginPercentage;
		}
		
		@Override
		@RosettaAttribute("fxHaircutPercentage")
		public BigDecimal getFxHaircutPercentage() {
			return fxHaircutPercentage;
		}
		
		@Override
		@RosettaAttribute("additionalHaircutPercentage")
		public BigDecimal getAdditionalHaircutPercentage() {
			return additionalHaircutPercentage;
		}
		
	
		@Override
		@RosettaAttribute("haircutPercentage")
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder setHaircutPercentage(BigDecimal haircutPercentage) {
			this.haircutPercentage = haircutPercentage==null?null:haircutPercentage;
			return this;
		}
		@Override
		@RosettaAttribute("marginPercentage")
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder setMarginPercentage(BigDecimal marginPercentage) {
			this.marginPercentage = marginPercentage==null?null:marginPercentage;
			return this;
		}
		@Override
		@RosettaAttribute("fxHaircutPercentage")
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder setFxHaircutPercentage(BigDecimal fxHaircutPercentage) {
			this.fxHaircutPercentage = fxHaircutPercentage==null?null:fxHaircutPercentage;
			return this;
		}
		@Override
		@RosettaAttribute("additionalHaircutPercentage")
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder setAdditionalHaircutPercentage(BigDecimal additionalHaircutPercentage) {
			this.additionalHaircutPercentage = additionalHaircutPercentage==null?null:additionalHaircutPercentage;
			return this;
		}
		
		@Override
		public CollateralValuationTreatment build() {
			return new CollateralValuationTreatment.CollateralValuationTreatmentImpl(this);
		}
		
		@Override
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getHaircutPercentage()!=null) return true;
			if (getMarginPercentage()!=null) return true;
			if (getFxHaircutPercentage()!=null) return true;
			if (getAdditionalHaircutPercentage()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralValuationTreatment.CollateralValuationTreatmentBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralValuationTreatment.CollateralValuationTreatmentBuilder o = (CollateralValuationTreatment.CollateralValuationTreatmentBuilder) other;
			
			
			merger.mergeBasic(getHaircutPercentage(), o.getHaircutPercentage(), this::setHaircutPercentage);
			merger.mergeBasic(getMarginPercentage(), o.getMarginPercentage(), this::setMarginPercentage);
			merger.mergeBasic(getFxHaircutPercentage(), o.getFxHaircutPercentage(), this::setFxHaircutPercentage);
			merger.mergeBasic(getAdditionalHaircutPercentage(), o.getAdditionalHaircutPercentage(), this::setAdditionalHaircutPercentage);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralValuationTreatment _that = getType().cast(o);
		
			if (!Objects.equals(haircutPercentage, _that.getHaircutPercentage())) return false;
			if (!Objects.equals(marginPercentage, _that.getMarginPercentage())) return false;
			if (!Objects.equals(fxHaircutPercentage, _that.getFxHaircutPercentage())) return false;
			if (!Objects.equals(additionalHaircutPercentage, _that.getAdditionalHaircutPercentage())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (haircutPercentage != null ? haircutPercentage.hashCode() : 0);
			_result = 31 * _result + (marginPercentage != null ? marginPercentage.hashCode() : 0);
			_result = 31 * _result + (fxHaircutPercentage != null ? fxHaircutPercentage.hashCode() : 0);
			_result = 31 * _result + (additionalHaircutPercentage != null ? additionalHaircutPercentage.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralValuationTreatmentBuilder {" +
				"haircutPercentage=" + this.haircutPercentage + ", " +
				"marginPercentage=" + this.marginPercentage + ", " +
				"fxHaircutPercentage=" + this.fxHaircutPercentage + ", " +
				"additionalHaircutPercentage=" + this.additionalHaircutPercentage +
			'}';
		}
	}
}
