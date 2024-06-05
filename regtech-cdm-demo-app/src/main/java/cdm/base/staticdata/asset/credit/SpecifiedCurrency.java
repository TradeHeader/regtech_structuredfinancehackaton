package cdm.base.staticdata.asset.credit;

import cdm.base.staticdata.asset.credit.SpecifiedCurrency;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency.SpecifiedCurrencyBuilder;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency.SpecifiedCurrencyBuilderImpl;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency.SpecifiedCurrencyImpl;
import cdm.base.staticdata.asset.credit.meta.SpecifiedCurrencyMeta;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="SpecifiedCurrency", builder=SpecifiedCurrency.SpecifiedCurrencyBuilderImpl.class, version="${project.version}")
public interface SpecifiedCurrency extends RosettaModelObject {

	SpecifiedCurrencyMeta metaData = new SpecifiedCurrencyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates whether the specified currency provision is applicable.
	 */
	Boolean getApplicable();
	/**
	 * The currency in which the specified currency is denominated. The list of valid currencies is not presently positioned as an enumeration as part of the CDM because that scope is limited to the values specified by ISDA and FpML. As a result, implementers have to make reference to the relevant standard, such as the ISO 4217 standard for currency codes.
	 */
	FieldWithMetaString getCurrency();

	/*********************** Build Methods  ***********************/
	SpecifiedCurrency build();
	
	SpecifiedCurrency.SpecifiedCurrencyBuilder toBuilder();
	
	static SpecifiedCurrency.SpecifiedCurrencyBuilder builder() {
		return new SpecifiedCurrency.SpecifiedCurrencyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SpecifiedCurrency> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SpecifiedCurrency> getType() {
		return SpecifiedCurrency.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
		processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.class, getCurrency());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SpecifiedCurrencyBuilder extends SpecifiedCurrency, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getCurrency();
		SpecifiedCurrency.SpecifiedCurrencyBuilder setApplicable(Boolean applicable);
		SpecifiedCurrency.SpecifiedCurrencyBuilder setCurrency(FieldWithMetaString currency0);
		SpecifiedCurrency.SpecifiedCurrencyBuilder setCurrencyValue(String currency1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
			processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency());
		}
		

		SpecifiedCurrency.SpecifiedCurrencyBuilder prune();
	}

	/*********************** Immutable Implementation of SpecifiedCurrency  ***********************/
	class SpecifiedCurrencyImpl implements SpecifiedCurrency {
		private final Boolean applicable;
		private final FieldWithMetaString currency;
		
		protected SpecifiedCurrencyImpl(SpecifiedCurrency.SpecifiedCurrencyBuilder builder) {
			this.applicable = builder.getApplicable();
			this.currency = ofNullable(builder.getCurrency()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString getCurrency() {
			return currency;
		}
		
		@Override
		public SpecifiedCurrency build() {
			return this;
		}
		
		@Override
		public SpecifiedCurrency.SpecifiedCurrencyBuilder toBuilder() {
			SpecifiedCurrency.SpecifiedCurrencyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SpecifiedCurrency.SpecifiedCurrencyBuilder builder) {
			ofNullable(getApplicable()).ifPresent(builder::setApplicable);
			ofNullable(getCurrency()).ifPresent(builder::setCurrency);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SpecifiedCurrency _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SpecifiedCurrency {" +
				"applicable=" + this.applicable + ", " +
				"currency=" + this.currency +
			'}';
		}
	}

	/*********************** Builder Implementation of SpecifiedCurrency  ***********************/
	class SpecifiedCurrencyBuilderImpl implements SpecifiedCurrency.SpecifiedCurrencyBuilder {
	
		protected Boolean applicable;
		protected FieldWithMetaString.FieldWithMetaStringBuilder currency;
	
		public SpecifiedCurrencyBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString.FieldWithMetaStringBuilder getCurrency() {
			return currency;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (currency!=null) {
				result = currency;
			}
			else {
				result = currency = FieldWithMetaString.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("applicable")
		public SpecifiedCurrency.SpecifiedCurrencyBuilder setApplicable(Boolean applicable) {
			this.applicable = applicable==null?null:applicable;
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public SpecifiedCurrency.SpecifiedCurrencyBuilder setCurrency(FieldWithMetaString currency) {
			this.currency = currency==null?null:currency.toBuilder();
			return this;
		}
		@Override
		public SpecifiedCurrency.SpecifiedCurrencyBuilder setCurrencyValue(String currency) {
			this.getOrCreateCurrency().setValue(currency);
			return this;
		}
		
		@Override
		public SpecifiedCurrency build() {
			return new SpecifiedCurrency.SpecifiedCurrencyImpl(this);
		}
		
		@Override
		public SpecifiedCurrency.SpecifiedCurrencyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SpecifiedCurrency.SpecifiedCurrencyBuilder prune() {
			if (currency!=null && !currency.prune().hasData()) currency = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getApplicable()!=null) return true;
			if (getCurrency()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SpecifiedCurrency.SpecifiedCurrencyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SpecifiedCurrency.SpecifiedCurrencyBuilder o = (SpecifiedCurrency.SpecifiedCurrencyBuilder) other;
			
			merger.mergeRosetta(getCurrency(), o.getCurrency(), this::setCurrency);
			
			merger.mergeBasic(getApplicable(), o.getApplicable(), this::setApplicable);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SpecifiedCurrency _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SpecifiedCurrencyBuilder {" +
				"applicable=" + this.applicable + ", " +
				"currency=" + this.currency +
			'}';
		}
	}
}
