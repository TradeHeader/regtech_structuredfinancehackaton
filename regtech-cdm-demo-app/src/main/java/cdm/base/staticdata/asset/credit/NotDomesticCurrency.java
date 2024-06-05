package cdm.base.staticdata.asset.credit;

import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
import cdm.base.staticdata.asset.credit.NotDomesticCurrency.NotDomesticCurrencyBuilder;
import cdm.base.staticdata.asset.credit.NotDomesticCurrency.NotDomesticCurrencyBuilderImpl;
import cdm.base.staticdata.asset.credit.NotDomesticCurrency.NotDomesticCurrencyImpl;
import cdm.base.staticdata.asset.credit.meta.NotDomesticCurrencyMeta;
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
 * A class to specify the ISDA 2003 Term: Not Domestic Currency.
 * @version ${project.version}
 */
@RosettaDataType(value="NotDomesticCurrency", builder=NotDomesticCurrency.NotDomesticCurrencyBuilderImpl.class, version="${project.version}")
public interface NotDomesticCurrency extends RosettaModelObject {

	NotDomesticCurrencyMeta metaData = new NotDomesticCurrencyMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates whether the Not Domestic Currency provision is applicable.
	 */
	Boolean getApplicable();
	/**
	 * An explicit specification of the domestic currency. The list of valid currencies is not presently positioned as an enumeration as part of the CDM because that scope is limited to the values specified by ISDA and FpML. As a result, implementers have to make reference to the relevant standard, such as the ISO 4217 standard for currency codes.
	 */
	FieldWithMetaString getCurrency();

	/*********************** Build Methods  ***********************/
	NotDomesticCurrency build();
	
	NotDomesticCurrency.NotDomesticCurrencyBuilder toBuilder();
	
	static NotDomesticCurrency.NotDomesticCurrencyBuilder builder() {
		return new NotDomesticCurrency.NotDomesticCurrencyBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends NotDomesticCurrency> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends NotDomesticCurrency> getType() {
		return NotDomesticCurrency.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
		processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.class, getCurrency());
	}
	

	/*********************** Builder Interface  ***********************/
	interface NotDomesticCurrencyBuilder extends NotDomesticCurrency, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getCurrency();
		NotDomesticCurrency.NotDomesticCurrencyBuilder setApplicable(Boolean applicable);
		NotDomesticCurrency.NotDomesticCurrencyBuilder setCurrency(FieldWithMetaString currency0);
		NotDomesticCurrency.NotDomesticCurrencyBuilder setCurrencyValue(String currency1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
			processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency());
		}
		

		NotDomesticCurrency.NotDomesticCurrencyBuilder prune();
	}

	/*********************** Immutable Implementation of NotDomesticCurrency  ***********************/
	class NotDomesticCurrencyImpl implements NotDomesticCurrency {
		private final Boolean applicable;
		private final FieldWithMetaString currency;
		
		protected NotDomesticCurrencyImpl(NotDomesticCurrency.NotDomesticCurrencyBuilder builder) {
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
		public NotDomesticCurrency build() {
			return this;
		}
		
		@Override
		public NotDomesticCurrency.NotDomesticCurrencyBuilder toBuilder() {
			NotDomesticCurrency.NotDomesticCurrencyBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(NotDomesticCurrency.NotDomesticCurrencyBuilder builder) {
			ofNullable(getApplicable()).ifPresent(builder::setApplicable);
			ofNullable(getCurrency()).ifPresent(builder::setCurrency);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NotDomesticCurrency _that = getType().cast(o);
		
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
			return "NotDomesticCurrency {" +
				"applicable=" + this.applicable + ", " +
				"currency=" + this.currency +
			'}';
		}
	}

	/*********************** Builder Implementation of NotDomesticCurrency  ***********************/
	class NotDomesticCurrencyBuilderImpl implements NotDomesticCurrency.NotDomesticCurrencyBuilder {
	
		protected Boolean applicable;
		protected FieldWithMetaString.FieldWithMetaStringBuilder currency;
	
		public NotDomesticCurrencyBuilderImpl() {
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
		public NotDomesticCurrency.NotDomesticCurrencyBuilder setApplicable(Boolean applicable) {
			this.applicable = applicable==null?null:applicable;
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public NotDomesticCurrency.NotDomesticCurrencyBuilder setCurrency(FieldWithMetaString currency) {
			this.currency = currency==null?null:currency.toBuilder();
			return this;
		}
		@Override
		public NotDomesticCurrency.NotDomesticCurrencyBuilder setCurrencyValue(String currency) {
			this.getOrCreateCurrency().setValue(currency);
			return this;
		}
		
		@Override
		public NotDomesticCurrency build() {
			return new NotDomesticCurrency.NotDomesticCurrencyImpl(this);
		}
		
		@Override
		public NotDomesticCurrency.NotDomesticCurrencyBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NotDomesticCurrency.NotDomesticCurrencyBuilder prune() {
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
		public NotDomesticCurrency.NotDomesticCurrencyBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			NotDomesticCurrency.NotDomesticCurrencyBuilder o = (NotDomesticCurrency.NotDomesticCurrencyBuilder) other;
			
			merger.mergeRosetta(getCurrency(), o.getCurrency(), this::setCurrency);
			
			merger.mergeBasic(getApplicable(), o.getApplicable(), this::setApplicable);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NotDomesticCurrency _that = getType().cast(o);
		
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
			return "NotDomesticCurrencyBuilder {" +
				"applicable=" + this.applicable + ", " +
				"currency=" + this.currency +
			'}';
		}
	}
}
