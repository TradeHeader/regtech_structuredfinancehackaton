package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.EquityMasterConfirmation;
import cdm.legaldocumentation.master.EquityMasterConfirmation.EquityMasterConfirmationBuilder;
import cdm.legaldocumentation.master.EquityMasterConfirmation.EquityMasterConfirmationBuilderImpl;
import cdm.legaldocumentation.master.EquityMasterConfirmation.EquityMasterConfirmationImpl;
import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018;
import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder;
import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018BuilderImpl;
import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Impl;
import cdm.legaldocumentation.master.meta.EquitySwapMasterConfirmation2018Meta;
import cdm.observable.asset.InterpolationMethodEnum;
import cdm.observable.asset.ValuationDates;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.ReturnTypeEnum;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.SettlementTerms;
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
 * Specification for the General Terms and Relationship Supplement Elections as provided in the 2018 ISDA CDM Equity Confirmation for Security Equity Swap.
 * @version ${project.version}
 */
@RosettaDataType(value="EquitySwapMasterConfirmation2018", builder=EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018BuilderImpl.class, version="${project.version}")
public interface EquitySwapMasterConfirmation2018 extends EquityMasterConfirmation {

	EquitySwapMasterConfirmation2018Meta metaData = new EquitySwapMasterConfirmation2018Meta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Per Part 1 Section 4, &#39;Dividend Obligations&#39;, of the 2018 ISDA CDM Equity Confirmation, Para 4.2 &#39;Dividend Returns&#39;
	 */
	ReturnTypeEnum getTypeOfSwapElection();
	/**
	 * Per Part 1 Section 5, &#39;Pricing&#39;, of the 2018 ISDA CDM Equity Confirmation, Para 5.1
	 */
	PriceReturnTerms getPricingMethodElection();
	/**
	 * Per Part 1 Section 3, &#39;Floating Obligations&#39;, of the 2018 ISDA CDM Equity Confirmation. Para 3.3
	 */
	InterpolationMethodEnum getLinearInterpolationElection();
	/**
	 * Per Part 1 Section 8, &#39;Settlement&#39;, of the 2018 ISDA CDM Equity Confirmation for Security Equity Swap
	 */
	SettlementTerms getSettlementTerms();
	/**
	 * The parameters used to generate the &#39;Equity Valuation Dates&#39; schedule, including the Effective Date and Termination Date for the Swap.
	 */
	ValuationDates getValuationDates();
	/**
	 * The parameters used to generate the payment date schedule, relative to the equityCalculationPeriod. Per Part 1 Section 12, &#39;Definitions&#39;, of the 2018 ISDA CDM Equity Confirmation. Para 73
	 */
	PaymentDates getEquityCashSettlementDates();

	/*********************** Build Methods  ***********************/
	EquitySwapMasterConfirmation2018 build();
	
	EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder toBuilder();
	
	static EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder builder() {
		return new EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018BuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EquitySwapMasterConfirmation2018> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EquitySwapMasterConfirmation2018> getType() {
		return EquitySwapMasterConfirmation2018.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("typeOfSwapElection"), ReturnTypeEnum.class, getTypeOfSwapElection(), this);
		processRosetta(path.newSubPath("pricingMethodElection"), processor, PriceReturnTerms.class, getPricingMethodElection());
		processor.processBasic(path.newSubPath("linearInterpolationElection"), InterpolationMethodEnum.class, getLinearInterpolationElection(), this);
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("valuationDates"), processor, ValuationDates.class, getValuationDates());
		processRosetta(path.newSubPath("equityCashSettlementDates"), processor, PaymentDates.class, getEquityCashSettlementDates());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EquitySwapMasterConfirmation2018Builder extends EquitySwapMasterConfirmation2018, EquityMasterConfirmation.EquityMasterConfirmationBuilder, RosettaModelObjectBuilder {
		PriceReturnTerms.PriceReturnTermsBuilder getOrCreatePricingMethodElection();
		PriceReturnTerms.PriceReturnTermsBuilder getPricingMethodElection();
		SettlementTerms.SettlementTermsBuilder getOrCreateSettlementTerms();
		SettlementTerms.SettlementTermsBuilder getSettlementTerms();
		ValuationDates.ValuationDatesBuilder getOrCreateValuationDates();
		ValuationDates.ValuationDatesBuilder getValuationDates();
		PaymentDates.PaymentDatesBuilder getOrCreateEquityCashSettlementDates();
		PaymentDates.PaymentDatesBuilder getEquityCashSettlementDates();
		EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setTypeOfSwapElection(ReturnTypeEnum typeOfSwapElection);
		EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setPricingMethodElection(PriceReturnTerms pricingMethodElection);
		EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setLinearInterpolationElection(InterpolationMethodEnum linearInterpolationElection);
		EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setSettlementTerms(SettlementTerms settlementTerms);
		EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setValuationDates(ValuationDates valuationDates);
		EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setEquityCashSettlementDates(PaymentDates equityCashSettlementDates);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("typeOfSwapElection"), ReturnTypeEnum.class, getTypeOfSwapElection(), this);
			processRosetta(path.newSubPath("pricingMethodElection"), processor, PriceReturnTerms.PriceReturnTermsBuilder.class, getPricingMethodElection());
			processor.processBasic(path.newSubPath("linearInterpolationElection"), InterpolationMethodEnum.class, getLinearInterpolationElection(), this);
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("valuationDates"), processor, ValuationDates.ValuationDatesBuilder.class, getValuationDates());
			processRosetta(path.newSubPath("equityCashSettlementDates"), processor, PaymentDates.PaymentDatesBuilder.class, getEquityCashSettlementDates());
		}
		

		EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder prune();
	}

	/*********************** Immutable Implementation of EquitySwapMasterConfirmation2018  ***********************/
	class EquitySwapMasterConfirmation2018Impl extends EquityMasterConfirmation.EquityMasterConfirmationImpl implements EquitySwapMasterConfirmation2018 {
		private final ReturnTypeEnum typeOfSwapElection;
		private final PriceReturnTerms pricingMethodElection;
		private final InterpolationMethodEnum linearInterpolationElection;
		private final SettlementTerms settlementTerms;
		private final ValuationDates valuationDates;
		private final PaymentDates equityCashSettlementDates;
		
		protected EquitySwapMasterConfirmation2018Impl(EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder builder) {
			super(builder);
			this.typeOfSwapElection = builder.getTypeOfSwapElection();
			this.pricingMethodElection = ofNullable(builder.getPricingMethodElection()).map(f->f.build()).orElse(null);
			this.linearInterpolationElection = builder.getLinearInterpolationElection();
			this.settlementTerms = ofNullable(builder.getSettlementTerms()).map(f->f.build()).orElse(null);
			this.valuationDates = ofNullable(builder.getValuationDates()).map(f->f.build()).orElse(null);
			this.equityCashSettlementDates = ofNullable(builder.getEquityCashSettlementDates()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("typeOfSwapElection")
		public ReturnTypeEnum getTypeOfSwapElection() {
			return typeOfSwapElection;
		}
		
		@Override
		@RosettaAttribute("pricingMethodElection")
		public PriceReturnTerms getPricingMethodElection() {
			return pricingMethodElection;
		}
		
		@Override
		@RosettaAttribute("linearInterpolationElection")
		public InterpolationMethodEnum getLinearInterpolationElection() {
			return linearInterpolationElection;
		}
		
		@Override
		@RosettaAttribute("settlementTerms")
		public SettlementTerms getSettlementTerms() {
			return settlementTerms;
		}
		
		@Override
		@RosettaAttribute("valuationDates")
		public ValuationDates getValuationDates() {
			return valuationDates;
		}
		
		@Override
		@RosettaAttribute("equityCashSettlementDates")
		public PaymentDates getEquityCashSettlementDates() {
			return equityCashSettlementDates;
		}
		
		@Override
		public EquitySwapMasterConfirmation2018 build() {
			return this;
		}
		
		@Override
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder toBuilder() {
			EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder builder) {
			super.setBuilderFields(builder);
			ofNullable(getTypeOfSwapElection()).ifPresent(builder::setTypeOfSwapElection);
			ofNullable(getPricingMethodElection()).ifPresent(builder::setPricingMethodElection);
			ofNullable(getLinearInterpolationElection()).ifPresent(builder::setLinearInterpolationElection);
			ofNullable(getSettlementTerms()).ifPresent(builder::setSettlementTerms);
			ofNullable(getValuationDates()).ifPresent(builder::setValuationDates);
			ofNullable(getEquityCashSettlementDates()).ifPresent(builder::setEquityCashSettlementDates);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			EquitySwapMasterConfirmation2018 _that = getType().cast(o);
		
			if (!Objects.equals(typeOfSwapElection, _that.getTypeOfSwapElection())) return false;
			if (!Objects.equals(pricingMethodElection, _that.getPricingMethodElection())) return false;
			if (!Objects.equals(linearInterpolationElection, _that.getLinearInterpolationElection())) return false;
			if (!Objects.equals(settlementTerms, _that.getSettlementTerms())) return false;
			if (!Objects.equals(valuationDates, _that.getValuationDates())) return false;
			if (!Objects.equals(equityCashSettlementDates, _that.getEquityCashSettlementDates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (typeOfSwapElection != null ? typeOfSwapElection.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (pricingMethodElection != null ? pricingMethodElection.hashCode() : 0);
			_result = 31 * _result + (linearInterpolationElection != null ? linearInterpolationElection.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (settlementTerms != null ? settlementTerms.hashCode() : 0);
			_result = 31 * _result + (valuationDates != null ? valuationDates.hashCode() : 0);
			_result = 31 * _result + (equityCashSettlementDates != null ? equityCashSettlementDates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquitySwapMasterConfirmation2018 {" +
				"typeOfSwapElection=" + this.typeOfSwapElection + ", " +
				"pricingMethodElection=" + this.pricingMethodElection + ", " +
				"linearInterpolationElection=" + this.linearInterpolationElection + ", " +
				"settlementTerms=" + this.settlementTerms + ", " +
				"valuationDates=" + this.valuationDates + ", " +
				"equityCashSettlementDates=" + this.equityCashSettlementDates +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of EquitySwapMasterConfirmation2018  ***********************/
	class EquitySwapMasterConfirmation2018BuilderImpl extends EquityMasterConfirmation.EquityMasterConfirmationBuilderImpl  implements EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder {
	
		protected ReturnTypeEnum typeOfSwapElection;
		protected PriceReturnTerms.PriceReturnTermsBuilder pricingMethodElection;
		protected InterpolationMethodEnum linearInterpolationElection;
		protected SettlementTerms.SettlementTermsBuilder settlementTerms;
		protected ValuationDates.ValuationDatesBuilder valuationDates;
		protected PaymentDates.PaymentDatesBuilder equityCashSettlementDates;
	
		public EquitySwapMasterConfirmation2018BuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("typeOfSwapElection")
		public ReturnTypeEnum getTypeOfSwapElection() {
			return typeOfSwapElection;
		}
		
		@Override
		@RosettaAttribute("pricingMethodElection")
		public PriceReturnTerms.PriceReturnTermsBuilder getPricingMethodElection() {
			return pricingMethodElection;
		}
		
		@Override
		public PriceReturnTerms.PriceReturnTermsBuilder getOrCreatePricingMethodElection() {
			PriceReturnTerms.PriceReturnTermsBuilder result;
			if (pricingMethodElection!=null) {
				result = pricingMethodElection;
			}
			else {
				result = pricingMethodElection = PriceReturnTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("linearInterpolationElection")
		public InterpolationMethodEnum getLinearInterpolationElection() {
			return linearInterpolationElection;
		}
		
		@Override
		@RosettaAttribute("settlementTerms")
		public SettlementTerms.SettlementTermsBuilder getSettlementTerms() {
			return settlementTerms;
		}
		
		@Override
		public SettlementTerms.SettlementTermsBuilder getOrCreateSettlementTerms() {
			SettlementTerms.SettlementTermsBuilder result;
			if (settlementTerms!=null) {
				result = settlementTerms;
			}
			else {
				result = settlementTerms = SettlementTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationDates")
		public ValuationDates.ValuationDatesBuilder getValuationDates() {
			return valuationDates;
		}
		
		@Override
		public ValuationDates.ValuationDatesBuilder getOrCreateValuationDates() {
			ValuationDates.ValuationDatesBuilder result;
			if (valuationDates!=null) {
				result = valuationDates;
			}
			else {
				result = valuationDates = ValuationDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("equityCashSettlementDates")
		public PaymentDates.PaymentDatesBuilder getEquityCashSettlementDates() {
			return equityCashSettlementDates;
		}
		
		@Override
		public PaymentDates.PaymentDatesBuilder getOrCreateEquityCashSettlementDates() {
			PaymentDates.PaymentDatesBuilder result;
			if (equityCashSettlementDates!=null) {
				result = equityCashSettlementDates;
			}
			else {
				result = equityCashSettlementDates = PaymentDates.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("typeOfSwapElection")
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setTypeOfSwapElection(ReturnTypeEnum typeOfSwapElection) {
			this.typeOfSwapElection = typeOfSwapElection==null?null:typeOfSwapElection;
			return this;
		}
		@Override
		@RosettaAttribute("pricingMethodElection")
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setPricingMethodElection(PriceReturnTerms pricingMethodElection) {
			this.pricingMethodElection = pricingMethodElection==null?null:pricingMethodElection.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("linearInterpolationElection")
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setLinearInterpolationElection(InterpolationMethodEnum linearInterpolationElection) {
			this.linearInterpolationElection = linearInterpolationElection==null?null:linearInterpolationElection;
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationDates")
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setValuationDates(ValuationDates valuationDates) {
			this.valuationDates = valuationDates==null?null:valuationDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("equityCashSettlementDates")
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder setEquityCashSettlementDates(PaymentDates equityCashSettlementDates) {
			this.equityCashSettlementDates = equityCashSettlementDates==null?null:equityCashSettlementDates.toBuilder();
			return this;
		}
		
		@Override
		public EquitySwapMasterConfirmation2018 build() {
			return new EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Impl(this);
		}
		
		@Override
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder prune() {
			super.prune();
			if (pricingMethodElection!=null && !pricingMethodElection.prune().hasData()) pricingMethodElection = null;
			if (settlementTerms!=null && !settlementTerms.prune().hasData()) settlementTerms = null;
			if (valuationDates!=null && !valuationDates.prune().hasData()) valuationDates = null;
			if (equityCashSettlementDates!=null && !equityCashSettlementDates.prune().hasData()) equityCashSettlementDates = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getTypeOfSwapElection()!=null) return true;
			if (getPricingMethodElection()!=null && getPricingMethodElection().hasData()) return true;
			if (getLinearInterpolationElection()!=null) return true;
			if (getSettlementTerms()!=null && getSettlementTerms().hasData()) return true;
			if (getValuationDates()!=null && getValuationDates().hasData()) return true;
			if (getEquityCashSettlementDates()!=null && getEquityCashSettlementDates().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder o = (EquitySwapMasterConfirmation2018.EquitySwapMasterConfirmation2018Builder) other;
			
			merger.mergeRosetta(getPricingMethodElection(), o.getPricingMethodElection(), this::setPricingMethodElection);
			merger.mergeRosetta(getSettlementTerms(), o.getSettlementTerms(), this::setSettlementTerms);
			merger.mergeRosetta(getValuationDates(), o.getValuationDates(), this::setValuationDates);
			merger.mergeRosetta(getEquityCashSettlementDates(), o.getEquityCashSettlementDates(), this::setEquityCashSettlementDates);
			
			merger.mergeBasic(getTypeOfSwapElection(), o.getTypeOfSwapElection(), this::setTypeOfSwapElection);
			merger.mergeBasic(getLinearInterpolationElection(), o.getLinearInterpolationElection(), this::setLinearInterpolationElection);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			EquitySwapMasterConfirmation2018 _that = getType().cast(o);
		
			if (!Objects.equals(typeOfSwapElection, _that.getTypeOfSwapElection())) return false;
			if (!Objects.equals(pricingMethodElection, _that.getPricingMethodElection())) return false;
			if (!Objects.equals(linearInterpolationElection, _that.getLinearInterpolationElection())) return false;
			if (!Objects.equals(settlementTerms, _that.getSettlementTerms())) return false;
			if (!Objects.equals(valuationDates, _that.getValuationDates())) return false;
			if (!Objects.equals(equityCashSettlementDates, _that.getEquityCashSettlementDates())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (typeOfSwapElection != null ? typeOfSwapElection.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (pricingMethodElection != null ? pricingMethodElection.hashCode() : 0);
			_result = 31 * _result + (linearInterpolationElection != null ? linearInterpolationElection.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (settlementTerms != null ? settlementTerms.hashCode() : 0);
			_result = 31 * _result + (valuationDates != null ? valuationDates.hashCode() : 0);
			_result = 31 * _result + (equityCashSettlementDates != null ? equityCashSettlementDates.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EquitySwapMasterConfirmation2018Builder {" +
				"typeOfSwapElection=" + this.typeOfSwapElection + ", " +
				"pricingMethodElection=" + this.pricingMethodElection + ", " +
				"linearInterpolationElection=" + this.linearInterpolationElection + ", " +
				"settlementTerms=" + this.settlementTerms + ", " +
				"valuationDates=" + this.valuationDates + ", " +
				"equityCashSettlementDates=" + this.equityCashSettlementDates +
			'}' + " " + super.toString();
		}
	}
}
