package cdm.product.template;

import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.VarianceReturnTerms;
import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.template.ReturnTerms;
import cdm.product.template.ReturnTerms.ReturnTermsBuilder;
import cdm.product.template.ReturnTerms.ReturnTermsBuilderImpl;
import cdm.product.template.ReturnTerms.ReturnTermsImpl;
import cdm.product.template.meta.ReturnTermsMeta;
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
 * Specifies the type of return of a performance payout.
 * @version ${project.version}
 */
@RosettaDataType(value="ReturnTerms", builder=ReturnTerms.ReturnTermsBuilderImpl.class, version="${project.version}")
public interface ReturnTerms extends RosettaModelObject {

	ReturnTermsMeta metaData = new ReturnTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Return terms based upon the underlier&#39;s observed price.
	 */
	PriceReturnTerms getPriceReturnTerms();
	/**
	 * Return terms based upon dividend payments associated to the underlier.
	 */
	DividendReturnTerms getDividendReturnTerms();
	/**
	 * Return terms based upon the observed variance of the underlier&#39;s price.
	 */
	VarianceReturnTerms getVarianceReturnTerms();
	/**
	 * Return terms based upon the observed volatility of the underlier&#39;s price.
	 */
	VolatilityReturnTerms getVolatilityReturnTerms();
	/**
	 * Return terms based upon the observed correlation between the components of the underlying basket.
	 */
	CorrelationReturnTerms getCorrelationReturnTerms();

	/*********************** Build Methods  ***********************/
	ReturnTerms build();
	
	ReturnTerms.ReturnTermsBuilder toBuilder();
	
	static ReturnTerms.ReturnTermsBuilder builder() {
		return new ReturnTerms.ReturnTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReturnTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReturnTerms> getType() {
		return ReturnTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("priceReturnTerms"), processor, PriceReturnTerms.class, getPriceReturnTerms());
		processRosetta(path.newSubPath("dividendReturnTerms"), processor, DividendReturnTerms.class, getDividendReturnTerms());
		processRosetta(path.newSubPath("varianceReturnTerms"), processor, VarianceReturnTerms.class, getVarianceReturnTerms());
		processRosetta(path.newSubPath("volatilityReturnTerms"), processor, VolatilityReturnTerms.class, getVolatilityReturnTerms());
		processRosetta(path.newSubPath("correlationReturnTerms"), processor, CorrelationReturnTerms.class, getCorrelationReturnTerms());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReturnTermsBuilder extends ReturnTerms, RosettaModelObjectBuilder {
		PriceReturnTerms.PriceReturnTermsBuilder getOrCreatePriceReturnTerms();
		PriceReturnTerms.PriceReturnTermsBuilder getPriceReturnTerms();
		DividendReturnTerms.DividendReturnTermsBuilder getOrCreateDividendReturnTerms();
		DividendReturnTerms.DividendReturnTermsBuilder getDividendReturnTerms();
		VarianceReturnTerms.VarianceReturnTermsBuilder getOrCreateVarianceReturnTerms();
		VarianceReturnTerms.VarianceReturnTermsBuilder getVarianceReturnTerms();
		VolatilityReturnTerms.VolatilityReturnTermsBuilder getOrCreateVolatilityReturnTerms();
		VolatilityReturnTerms.VolatilityReturnTermsBuilder getVolatilityReturnTerms();
		CorrelationReturnTerms.CorrelationReturnTermsBuilder getOrCreateCorrelationReturnTerms();
		CorrelationReturnTerms.CorrelationReturnTermsBuilder getCorrelationReturnTerms();
		ReturnTerms.ReturnTermsBuilder setPriceReturnTerms(PriceReturnTerms priceReturnTerms);
		ReturnTerms.ReturnTermsBuilder setDividendReturnTerms(DividendReturnTerms dividendReturnTerms);
		ReturnTerms.ReturnTermsBuilder setVarianceReturnTerms(VarianceReturnTerms varianceReturnTerms);
		ReturnTerms.ReturnTermsBuilder setVolatilityReturnTerms(VolatilityReturnTerms volatilityReturnTerms);
		ReturnTerms.ReturnTermsBuilder setCorrelationReturnTerms(CorrelationReturnTerms correlationReturnTerms);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("priceReturnTerms"), processor, PriceReturnTerms.PriceReturnTermsBuilder.class, getPriceReturnTerms());
			processRosetta(path.newSubPath("dividendReturnTerms"), processor, DividendReturnTerms.DividendReturnTermsBuilder.class, getDividendReturnTerms());
			processRosetta(path.newSubPath("varianceReturnTerms"), processor, VarianceReturnTerms.VarianceReturnTermsBuilder.class, getVarianceReturnTerms());
			processRosetta(path.newSubPath("volatilityReturnTerms"), processor, VolatilityReturnTerms.VolatilityReturnTermsBuilder.class, getVolatilityReturnTerms());
			processRosetta(path.newSubPath("correlationReturnTerms"), processor, CorrelationReturnTerms.CorrelationReturnTermsBuilder.class, getCorrelationReturnTerms());
		}
		

		ReturnTerms.ReturnTermsBuilder prune();
	}

	/*********************** Immutable Implementation of ReturnTerms  ***********************/
	class ReturnTermsImpl implements ReturnTerms {
		private final PriceReturnTerms priceReturnTerms;
		private final DividendReturnTerms dividendReturnTerms;
		private final VarianceReturnTerms varianceReturnTerms;
		private final VolatilityReturnTerms volatilityReturnTerms;
		private final CorrelationReturnTerms correlationReturnTerms;
		
		protected ReturnTermsImpl(ReturnTerms.ReturnTermsBuilder builder) {
			this.priceReturnTerms = ofNullable(builder.getPriceReturnTerms()).map(f->f.build()).orElse(null);
			this.dividendReturnTerms = ofNullable(builder.getDividendReturnTerms()).map(f->f.build()).orElse(null);
			this.varianceReturnTerms = ofNullable(builder.getVarianceReturnTerms()).map(f->f.build()).orElse(null);
			this.volatilityReturnTerms = ofNullable(builder.getVolatilityReturnTerms()).map(f->f.build()).orElse(null);
			this.correlationReturnTerms = ofNullable(builder.getCorrelationReturnTerms()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("priceReturnTerms")
		public PriceReturnTerms getPriceReturnTerms() {
			return priceReturnTerms;
		}
		
		@Override
		@RosettaAttribute("dividendReturnTerms")
		public DividendReturnTerms getDividendReturnTerms() {
			return dividendReturnTerms;
		}
		
		@Override
		@RosettaAttribute("varianceReturnTerms")
		public VarianceReturnTerms getVarianceReturnTerms() {
			return varianceReturnTerms;
		}
		
		@Override
		@RosettaAttribute("volatilityReturnTerms")
		public VolatilityReturnTerms getVolatilityReturnTerms() {
			return volatilityReturnTerms;
		}
		
		@Override
		@RosettaAttribute("correlationReturnTerms")
		public CorrelationReturnTerms getCorrelationReturnTerms() {
			return correlationReturnTerms;
		}
		
		@Override
		public ReturnTerms build() {
			return this;
		}
		
		@Override
		public ReturnTerms.ReturnTermsBuilder toBuilder() {
			ReturnTerms.ReturnTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReturnTerms.ReturnTermsBuilder builder) {
			ofNullable(getPriceReturnTerms()).ifPresent(builder::setPriceReturnTerms);
			ofNullable(getDividendReturnTerms()).ifPresent(builder::setDividendReturnTerms);
			ofNullable(getVarianceReturnTerms()).ifPresent(builder::setVarianceReturnTerms);
			ofNullable(getVolatilityReturnTerms()).ifPresent(builder::setVolatilityReturnTerms);
			ofNullable(getCorrelationReturnTerms()).ifPresent(builder::setCorrelationReturnTerms);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(priceReturnTerms, _that.getPriceReturnTerms())) return false;
			if (!Objects.equals(dividendReturnTerms, _that.getDividendReturnTerms())) return false;
			if (!Objects.equals(varianceReturnTerms, _that.getVarianceReturnTerms())) return false;
			if (!Objects.equals(volatilityReturnTerms, _that.getVolatilityReturnTerms())) return false;
			if (!Objects.equals(correlationReturnTerms, _that.getCorrelationReturnTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (priceReturnTerms != null ? priceReturnTerms.hashCode() : 0);
			_result = 31 * _result + (dividendReturnTerms != null ? dividendReturnTerms.hashCode() : 0);
			_result = 31 * _result + (varianceReturnTerms != null ? varianceReturnTerms.hashCode() : 0);
			_result = 31 * _result + (volatilityReturnTerms != null ? volatilityReturnTerms.hashCode() : 0);
			_result = 31 * _result + (correlationReturnTerms != null ? correlationReturnTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReturnTerms {" +
				"priceReturnTerms=" + this.priceReturnTerms + ", " +
				"dividendReturnTerms=" + this.dividendReturnTerms + ", " +
				"varianceReturnTerms=" + this.varianceReturnTerms + ", " +
				"volatilityReturnTerms=" + this.volatilityReturnTerms + ", " +
				"correlationReturnTerms=" + this.correlationReturnTerms +
			'}';
		}
	}

	/*********************** Builder Implementation of ReturnTerms  ***********************/
	class ReturnTermsBuilderImpl implements ReturnTerms.ReturnTermsBuilder {
	
		protected PriceReturnTerms.PriceReturnTermsBuilder priceReturnTerms;
		protected DividendReturnTerms.DividendReturnTermsBuilder dividendReturnTerms;
		protected VarianceReturnTerms.VarianceReturnTermsBuilder varianceReturnTerms;
		protected VolatilityReturnTerms.VolatilityReturnTermsBuilder volatilityReturnTerms;
		protected CorrelationReturnTerms.CorrelationReturnTermsBuilder correlationReturnTerms;
	
		public ReturnTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("priceReturnTerms")
		public PriceReturnTerms.PriceReturnTermsBuilder getPriceReturnTerms() {
			return priceReturnTerms;
		}
		
		@Override
		public PriceReturnTerms.PriceReturnTermsBuilder getOrCreatePriceReturnTerms() {
			PriceReturnTerms.PriceReturnTermsBuilder result;
			if (priceReturnTerms!=null) {
				result = priceReturnTerms;
			}
			else {
				result = priceReturnTerms = PriceReturnTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dividendReturnTerms")
		public DividendReturnTerms.DividendReturnTermsBuilder getDividendReturnTerms() {
			return dividendReturnTerms;
		}
		
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder getOrCreateDividendReturnTerms() {
			DividendReturnTerms.DividendReturnTermsBuilder result;
			if (dividendReturnTerms!=null) {
				result = dividendReturnTerms;
			}
			else {
				result = dividendReturnTerms = DividendReturnTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("varianceReturnTerms")
		public VarianceReturnTerms.VarianceReturnTermsBuilder getVarianceReturnTerms() {
			return varianceReturnTerms;
		}
		
		@Override
		public VarianceReturnTerms.VarianceReturnTermsBuilder getOrCreateVarianceReturnTerms() {
			VarianceReturnTerms.VarianceReturnTermsBuilder result;
			if (varianceReturnTerms!=null) {
				result = varianceReturnTerms;
			}
			else {
				result = varianceReturnTerms = VarianceReturnTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("volatilityReturnTerms")
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder getVolatilityReturnTerms() {
			return volatilityReturnTerms;
		}
		
		@Override
		public VolatilityReturnTerms.VolatilityReturnTermsBuilder getOrCreateVolatilityReturnTerms() {
			VolatilityReturnTerms.VolatilityReturnTermsBuilder result;
			if (volatilityReturnTerms!=null) {
				result = volatilityReturnTerms;
			}
			else {
				result = volatilityReturnTerms = VolatilityReturnTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("correlationReturnTerms")
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder getCorrelationReturnTerms() {
			return correlationReturnTerms;
		}
		
		@Override
		public CorrelationReturnTerms.CorrelationReturnTermsBuilder getOrCreateCorrelationReturnTerms() {
			CorrelationReturnTerms.CorrelationReturnTermsBuilder result;
			if (correlationReturnTerms!=null) {
				result = correlationReturnTerms;
			}
			else {
				result = correlationReturnTerms = CorrelationReturnTerms.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("priceReturnTerms")
		public ReturnTerms.ReturnTermsBuilder setPriceReturnTerms(PriceReturnTerms priceReturnTerms) {
			this.priceReturnTerms = priceReturnTerms==null?null:priceReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dividendReturnTerms")
		public ReturnTerms.ReturnTermsBuilder setDividendReturnTerms(DividendReturnTerms dividendReturnTerms) {
			this.dividendReturnTerms = dividendReturnTerms==null?null:dividendReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("varianceReturnTerms")
		public ReturnTerms.ReturnTermsBuilder setVarianceReturnTerms(VarianceReturnTerms varianceReturnTerms) {
			this.varianceReturnTerms = varianceReturnTerms==null?null:varianceReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("volatilityReturnTerms")
		public ReturnTerms.ReturnTermsBuilder setVolatilityReturnTerms(VolatilityReturnTerms volatilityReturnTerms) {
			this.volatilityReturnTerms = volatilityReturnTerms==null?null:volatilityReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("correlationReturnTerms")
		public ReturnTerms.ReturnTermsBuilder setCorrelationReturnTerms(CorrelationReturnTerms correlationReturnTerms) {
			this.correlationReturnTerms = correlationReturnTerms==null?null:correlationReturnTerms.toBuilder();
			return this;
		}
		
		@Override
		public ReturnTerms build() {
			return new ReturnTerms.ReturnTermsImpl(this);
		}
		
		@Override
		public ReturnTerms.ReturnTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReturnTerms.ReturnTermsBuilder prune() {
			if (priceReturnTerms!=null && !priceReturnTerms.prune().hasData()) priceReturnTerms = null;
			if (dividendReturnTerms!=null && !dividendReturnTerms.prune().hasData()) dividendReturnTerms = null;
			if (varianceReturnTerms!=null && !varianceReturnTerms.prune().hasData()) varianceReturnTerms = null;
			if (volatilityReturnTerms!=null && !volatilityReturnTerms.prune().hasData()) volatilityReturnTerms = null;
			if (correlationReturnTerms!=null && !correlationReturnTerms.prune().hasData()) correlationReturnTerms = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPriceReturnTerms()!=null && getPriceReturnTerms().hasData()) return true;
			if (getDividendReturnTerms()!=null && getDividendReturnTerms().hasData()) return true;
			if (getVarianceReturnTerms()!=null && getVarianceReturnTerms().hasData()) return true;
			if (getVolatilityReturnTerms()!=null && getVolatilityReturnTerms().hasData()) return true;
			if (getCorrelationReturnTerms()!=null && getCorrelationReturnTerms().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReturnTerms.ReturnTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReturnTerms.ReturnTermsBuilder o = (ReturnTerms.ReturnTermsBuilder) other;
			
			merger.mergeRosetta(getPriceReturnTerms(), o.getPriceReturnTerms(), this::setPriceReturnTerms);
			merger.mergeRosetta(getDividendReturnTerms(), o.getDividendReturnTerms(), this::setDividendReturnTerms);
			merger.mergeRosetta(getVarianceReturnTerms(), o.getVarianceReturnTerms(), this::setVarianceReturnTerms);
			merger.mergeRosetta(getVolatilityReturnTerms(), o.getVolatilityReturnTerms(), this::setVolatilityReturnTerms);
			merger.mergeRosetta(getCorrelationReturnTerms(), o.getCorrelationReturnTerms(), this::setCorrelationReturnTerms);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(priceReturnTerms, _that.getPriceReturnTerms())) return false;
			if (!Objects.equals(dividendReturnTerms, _that.getDividendReturnTerms())) return false;
			if (!Objects.equals(varianceReturnTerms, _that.getVarianceReturnTerms())) return false;
			if (!Objects.equals(volatilityReturnTerms, _that.getVolatilityReturnTerms())) return false;
			if (!Objects.equals(correlationReturnTerms, _that.getCorrelationReturnTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (priceReturnTerms != null ? priceReturnTerms.hashCode() : 0);
			_result = 31 * _result + (dividendReturnTerms != null ? dividendReturnTerms.hashCode() : 0);
			_result = 31 * _result + (varianceReturnTerms != null ? varianceReturnTerms.hashCode() : 0);
			_result = 31 * _result + (volatilityReturnTerms != null ? volatilityReturnTerms.hashCode() : 0);
			_result = 31 * _result + (correlationReturnTerms != null ? correlationReturnTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReturnTermsBuilder {" +
				"priceReturnTerms=" + this.priceReturnTerms + ", " +
				"dividendReturnTerms=" + this.dividendReturnTerms + ", " +
				"varianceReturnTerms=" + this.varianceReturnTerms + ", " +
				"volatilityReturnTerms=" + this.volatilityReturnTerms + ", " +
				"correlationReturnTerms=" + this.correlationReturnTerms +
			'}';
		}
	}
}
