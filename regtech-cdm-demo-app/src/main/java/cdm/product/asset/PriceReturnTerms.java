package cdm.product.asset;

import cdm.observable.asset.PriceSchedule;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.PriceReturnTerms.PriceReturnTermsBuilder;
import cdm.product.asset.PriceReturnTerms.PriceReturnTermsBuilderImpl;
import cdm.product.asset.PriceReturnTerms.PriceReturnTermsImpl;
import cdm.product.asset.ReturnTypeEnum;
import cdm.product.asset.meta.PriceReturnTermsMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="PriceReturnTerms", builder=PriceReturnTerms.PriceReturnTermsBuilderImpl.class, version="${project.version}")
public interface PriceReturnTerms extends RosettaModelObject {

	PriceReturnTermsMeta metaData = new PriceReturnTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the initial valuation price(s) of the underlier. This price can be expressed either as an actual amount/currency, as a determination method, or by reference to another value specified in the swap document.
	 */
	PriceSchedule getValuationPriceInitial();
	/**
	 * 2018 ISDA CDM Equity Confirmation for Security Equity Swap: Final Price | Specifies the final valuation price of the underlier. This price can be expressed either as an actual amount/currency, as a determination method, or by reference to another value specified in the swap document.
	 */
	PriceSchedule getValuationPriceFinal();
	/**
	 * The type of return associated with the equity swap.
	 */
	ReturnTypeEnum getReturnType();
	/**
	 * Defines the conversion applied if the quantity unit on contract is different from unit on referenced underlier.
	 */
	BigDecimal getConversionFactor();
	/**
	 * Performance calculation, in accordance with Part 1 Section 12 of the 2018 ISDA CDM Equity Confirmation for Security Equity Swap, Para 75. &#39;Equity Performance&#39;. Cumulative performance is used as a notional multiplier factor on both legs of an Equity Swap.
	 */
	String getPerformance();

	/*********************** Build Methods  ***********************/
	PriceReturnTerms build();
	
	PriceReturnTerms.PriceReturnTermsBuilder toBuilder();
	
	static PriceReturnTerms.PriceReturnTermsBuilder builder() {
		return new PriceReturnTerms.PriceReturnTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PriceReturnTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PriceReturnTerms> getType() {
		return PriceReturnTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("valuationPriceInitial"), processor, PriceSchedule.class, getValuationPriceInitial());
		processRosetta(path.newSubPath("valuationPriceFinal"), processor, PriceSchedule.class, getValuationPriceFinal());
		processor.processBasic(path.newSubPath("returnType"), ReturnTypeEnum.class, getReturnType(), this);
		processor.processBasic(path.newSubPath("conversionFactor"), BigDecimal.class, getConversionFactor(), this);
		processor.processBasic(path.newSubPath("performance"), String.class, getPerformance(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PriceReturnTermsBuilder extends PriceReturnTerms, RosettaModelObjectBuilder {
		PriceSchedule.PriceScheduleBuilder getOrCreateValuationPriceInitial();
		PriceSchedule.PriceScheduleBuilder getValuationPriceInitial();
		PriceSchedule.PriceScheduleBuilder getOrCreateValuationPriceFinal();
		PriceSchedule.PriceScheduleBuilder getValuationPriceFinal();
		PriceReturnTerms.PriceReturnTermsBuilder setValuationPriceInitial(PriceSchedule valuationPriceInitial);
		PriceReturnTerms.PriceReturnTermsBuilder setValuationPriceFinal(PriceSchedule valuationPriceFinal);
		PriceReturnTerms.PriceReturnTermsBuilder setReturnType(ReturnTypeEnum returnType);
		PriceReturnTerms.PriceReturnTermsBuilder setConversionFactor(BigDecimal conversionFactor);
		PriceReturnTerms.PriceReturnTermsBuilder setPerformance(String performance);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("valuationPriceInitial"), processor, PriceSchedule.PriceScheduleBuilder.class, getValuationPriceInitial());
			processRosetta(path.newSubPath("valuationPriceFinal"), processor, PriceSchedule.PriceScheduleBuilder.class, getValuationPriceFinal());
			processor.processBasic(path.newSubPath("returnType"), ReturnTypeEnum.class, getReturnType(), this);
			processor.processBasic(path.newSubPath("conversionFactor"), BigDecimal.class, getConversionFactor(), this);
			processor.processBasic(path.newSubPath("performance"), String.class, getPerformance(), this);
		}
		

		PriceReturnTerms.PriceReturnTermsBuilder prune();
	}

	/*********************** Immutable Implementation of PriceReturnTerms  ***********************/
	class PriceReturnTermsImpl implements PriceReturnTerms {
		private final PriceSchedule valuationPriceInitial;
		private final PriceSchedule valuationPriceFinal;
		private final ReturnTypeEnum returnType;
		private final BigDecimal conversionFactor;
		private final String performance;
		
		protected PriceReturnTermsImpl(PriceReturnTerms.PriceReturnTermsBuilder builder) {
			this.valuationPriceInitial = ofNullable(builder.getValuationPriceInitial()).map(f->f.build()).orElse(null);
			this.valuationPriceFinal = ofNullable(builder.getValuationPriceFinal()).map(f->f.build()).orElse(null);
			this.returnType = builder.getReturnType();
			this.conversionFactor = builder.getConversionFactor();
			this.performance = builder.getPerformance();
		}
		
		@Override
		@RosettaAttribute("valuationPriceInitial")
		public PriceSchedule getValuationPriceInitial() {
			return valuationPriceInitial;
		}
		
		@Override
		@RosettaAttribute("valuationPriceFinal")
		public PriceSchedule getValuationPriceFinal() {
			return valuationPriceFinal;
		}
		
		@Override
		@RosettaAttribute("returnType")
		public ReturnTypeEnum getReturnType() {
			return returnType;
		}
		
		@Override
		@RosettaAttribute("conversionFactor")
		public BigDecimal getConversionFactor() {
			return conversionFactor;
		}
		
		@Override
		@RosettaAttribute("performance")
		public String getPerformance() {
			return performance;
		}
		
		@Override
		public PriceReturnTerms build() {
			return this;
		}
		
		@Override
		public PriceReturnTerms.PriceReturnTermsBuilder toBuilder() {
			PriceReturnTerms.PriceReturnTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PriceReturnTerms.PriceReturnTermsBuilder builder) {
			ofNullable(getValuationPriceInitial()).ifPresent(builder::setValuationPriceInitial);
			ofNullable(getValuationPriceFinal()).ifPresent(builder::setValuationPriceFinal);
			ofNullable(getReturnType()).ifPresent(builder::setReturnType);
			ofNullable(getConversionFactor()).ifPresent(builder::setConversionFactor);
			ofNullable(getPerformance()).ifPresent(builder::setPerformance);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PriceReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(valuationPriceInitial, _that.getValuationPriceInitial())) return false;
			if (!Objects.equals(valuationPriceFinal, _that.getValuationPriceFinal())) return false;
			if (!Objects.equals(returnType, _that.getReturnType())) return false;
			if (!Objects.equals(conversionFactor, _that.getConversionFactor())) return false;
			if (!Objects.equals(performance, _that.getPerformance())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationPriceInitial != null ? valuationPriceInitial.hashCode() : 0);
			_result = 31 * _result + (valuationPriceFinal != null ? valuationPriceFinal.hashCode() : 0);
			_result = 31 * _result + (returnType != null ? returnType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (conversionFactor != null ? conversionFactor.hashCode() : 0);
			_result = 31 * _result + (performance != null ? performance.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceReturnTerms {" +
				"valuationPriceInitial=" + this.valuationPriceInitial + ", " +
				"valuationPriceFinal=" + this.valuationPriceFinal + ", " +
				"returnType=" + this.returnType + ", " +
				"conversionFactor=" + this.conversionFactor + ", " +
				"performance=" + this.performance +
			'}';
		}
	}

	/*********************** Builder Implementation of PriceReturnTerms  ***********************/
	class PriceReturnTermsBuilderImpl implements PriceReturnTerms.PriceReturnTermsBuilder {
	
		protected PriceSchedule.PriceScheduleBuilder valuationPriceInitial;
		protected PriceSchedule.PriceScheduleBuilder valuationPriceFinal;
		protected ReturnTypeEnum returnType;
		protected BigDecimal conversionFactor;
		protected String performance;
	
		public PriceReturnTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("valuationPriceInitial")
		public PriceSchedule.PriceScheduleBuilder getValuationPriceInitial() {
			return valuationPriceInitial;
		}
		
		@Override
		public PriceSchedule.PriceScheduleBuilder getOrCreateValuationPriceInitial() {
			PriceSchedule.PriceScheduleBuilder result;
			if (valuationPriceInitial!=null) {
				result = valuationPriceInitial;
			}
			else {
				result = valuationPriceInitial = PriceSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("valuationPriceFinal")
		public PriceSchedule.PriceScheduleBuilder getValuationPriceFinal() {
			return valuationPriceFinal;
		}
		
		@Override
		public PriceSchedule.PriceScheduleBuilder getOrCreateValuationPriceFinal() {
			PriceSchedule.PriceScheduleBuilder result;
			if (valuationPriceFinal!=null) {
				result = valuationPriceFinal;
			}
			else {
				result = valuationPriceFinal = PriceSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("returnType")
		public ReturnTypeEnum getReturnType() {
			return returnType;
		}
		
		@Override
		@RosettaAttribute("conversionFactor")
		public BigDecimal getConversionFactor() {
			return conversionFactor;
		}
		
		@Override
		@RosettaAttribute("performance")
		public String getPerformance() {
			return performance;
		}
		
	
		@Override
		@RosettaAttribute("valuationPriceInitial")
		public PriceReturnTerms.PriceReturnTermsBuilder setValuationPriceInitial(PriceSchedule valuationPriceInitial) {
			this.valuationPriceInitial = valuationPriceInitial==null?null:valuationPriceInitial.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("valuationPriceFinal")
		public PriceReturnTerms.PriceReturnTermsBuilder setValuationPriceFinal(PriceSchedule valuationPriceFinal) {
			this.valuationPriceFinal = valuationPriceFinal==null?null:valuationPriceFinal.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("returnType")
		public PriceReturnTerms.PriceReturnTermsBuilder setReturnType(ReturnTypeEnum returnType) {
			this.returnType = returnType==null?null:returnType;
			return this;
		}
		@Override
		@RosettaAttribute("conversionFactor")
		public PriceReturnTerms.PriceReturnTermsBuilder setConversionFactor(BigDecimal conversionFactor) {
			this.conversionFactor = conversionFactor==null?null:conversionFactor;
			return this;
		}
		@Override
		@RosettaAttribute("performance")
		public PriceReturnTerms.PriceReturnTermsBuilder setPerformance(String performance) {
			this.performance = performance==null?null:performance;
			return this;
		}
		
		@Override
		public PriceReturnTerms build() {
			return new PriceReturnTerms.PriceReturnTermsImpl(this);
		}
		
		@Override
		public PriceReturnTerms.PriceReturnTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PriceReturnTerms.PriceReturnTermsBuilder prune() {
			if (valuationPriceInitial!=null && !valuationPriceInitial.prune().hasData()) valuationPriceInitial = null;
			if (valuationPriceFinal!=null && !valuationPriceFinal.prune().hasData()) valuationPriceFinal = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValuationPriceInitial()!=null && getValuationPriceInitial().hasData()) return true;
			if (getValuationPriceFinal()!=null && getValuationPriceFinal().hasData()) return true;
			if (getReturnType()!=null) return true;
			if (getConversionFactor()!=null) return true;
			if (getPerformance()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PriceReturnTerms.PriceReturnTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PriceReturnTerms.PriceReturnTermsBuilder o = (PriceReturnTerms.PriceReturnTermsBuilder) other;
			
			merger.mergeRosetta(getValuationPriceInitial(), o.getValuationPriceInitial(), this::setValuationPriceInitial);
			merger.mergeRosetta(getValuationPriceFinal(), o.getValuationPriceFinal(), this::setValuationPriceFinal);
			
			merger.mergeBasic(getReturnType(), o.getReturnType(), this::setReturnType);
			merger.mergeBasic(getConversionFactor(), o.getConversionFactor(), this::setConversionFactor);
			merger.mergeBasic(getPerformance(), o.getPerformance(), this::setPerformance);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PriceReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(valuationPriceInitial, _that.getValuationPriceInitial())) return false;
			if (!Objects.equals(valuationPriceFinal, _that.getValuationPriceFinal())) return false;
			if (!Objects.equals(returnType, _that.getReturnType())) return false;
			if (!Objects.equals(conversionFactor, _that.getConversionFactor())) return false;
			if (!Objects.equals(performance, _that.getPerformance())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationPriceInitial != null ? valuationPriceInitial.hashCode() : 0);
			_result = 31 * _result + (valuationPriceFinal != null ? valuationPriceFinal.hashCode() : 0);
			_result = 31 * _result + (returnType != null ? returnType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (conversionFactor != null ? conversionFactor.hashCode() : 0);
			_result = 31 * _result + (performance != null ? performance.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceReturnTermsBuilder {" +
				"valuationPriceInitial=" + this.valuationPriceInitial + ", " +
				"valuationPriceFinal=" + this.valuationPriceFinal + ", " +
				"returnType=" + this.returnType + ", " +
				"conversionFactor=" + this.conversionFactor + ", " +
				"performance=" + this.performance +
			'}';
		}
	}
}
