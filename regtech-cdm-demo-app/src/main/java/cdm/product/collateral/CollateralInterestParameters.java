package cdm.product.collateral;

import cdm.base.datetime.CalculationFrequency;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.product.collateral.CollateralInterestCalculationParameters;
import cdm.product.collateral.CollateralInterestHandlingParameters;
import cdm.product.collateral.CollateralInterestParameters;
import cdm.product.collateral.CollateralInterestParameters.CollateralInterestParametersBuilder;
import cdm.product.collateral.CollateralInterestParameters.CollateralInterestParametersBuilderImpl;
import cdm.product.collateral.CollateralInterestParameters.CollateralInterestParametersImpl;
import cdm.product.collateral.CollateralMarginTypeEnum;
import cdm.product.collateral.meta.CollateralInterestParametersMeta;
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
 * Represents the floating interest calculation and distribution parameters for a single currency.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralInterestParameters", builder=CollateralInterestParameters.CollateralInterestParametersBuilderImpl.class, version="${project.version}")
public interface CollateralInterestParameters extends RosettaModelObject {

	CollateralInterestParametersMeta metaData = new CollateralInterestParametersMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the party to which these parameters apply (the applicable party).  In other words, if the parameters are different depending on which party is posting/holding the collateral, for which party to the Collateral Agreement (Party 1 or Party 2) that is posting the collateral do these parameters apply?
	 */
	CounterpartyRoleEnum getPostingParty();
	/**
	 * Specifies the type of margin for which interest is being calculated, if the parameters are different depending on type of margin (initial or variation).
	 */
	CollateralMarginTypeEnum getMarginType();
	/**
	 * Specifies the currency for which the parameters are captured.
	 */
	String getCurrency();
	/**
	 * Represents the basic interest calculation parameters.
	 */
	CollateralInterestCalculationParameters getInterestCalculationParameters();
	/**
	 * Represents how often and when interest is calculated.
	 */
	CalculationFrequency getInterestCalculationFrequency();
	/**
	 * Represents the parameters describing how and when interest transfer occurs.
	 */
	CollateralInterestHandlingParameters getInterestHandlingParameters();

	/*********************** Build Methods  ***********************/
	CollateralInterestParameters build();
	
	CollateralInterestParameters.CollateralInterestParametersBuilder toBuilder();
	
	static CollateralInterestParameters.CollateralInterestParametersBuilder builder() {
		return new CollateralInterestParameters.CollateralInterestParametersBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralInterestParameters> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralInterestParameters> getType() {
		return CollateralInterestParameters.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("postingParty"), CounterpartyRoleEnum.class, getPostingParty(), this);
		processor.processBasic(path.newSubPath("marginType"), CollateralMarginTypeEnum.class, getMarginType(), this);
		processor.processBasic(path.newSubPath("currency"), String.class, getCurrency(), this);
		processRosetta(path.newSubPath("interestCalculationParameters"), processor, CollateralInterestCalculationParameters.class, getInterestCalculationParameters());
		processRosetta(path.newSubPath("interestCalculationFrequency"), processor, CalculationFrequency.class, getInterestCalculationFrequency());
		processRosetta(path.newSubPath("interestHandlingParameters"), processor, CollateralInterestHandlingParameters.class, getInterestHandlingParameters());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralInterestParametersBuilder extends CollateralInterestParameters, RosettaModelObjectBuilder {
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder getOrCreateInterestCalculationParameters();
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder getInterestCalculationParameters();
		CalculationFrequency.CalculationFrequencyBuilder getOrCreateInterestCalculationFrequency();
		CalculationFrequency.CalculationFrequencyBuilder getInterestCalculationFrequency();
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder getOrCreateInterestHandlingParameters();
		CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder getInterestHandlingParameters();
		CollateralInterestParameters.CollateralInterestParametersBuilder setPostingParty(CounterpartyRoleEnum postingParty);
		CollateralInterestParameters.CollateralInterestParametersBuilder setMarginType(CollateralMarginTypeEnum marginType);
		CollateralInterestParameters.CollateralInterestParametersBuilder setCurrency(String currency);
		CollateralInterestParameters.CollateralInterestParametersBuilder setInterestCalculationParameters(CollateralInterestCalculationParameters interestCalculationParameters);
		CollateralInterestParameters.CollateralInterestParametersBuilder setInterestCalculationFrequency(CalculationFrequency interestCalculationFrequency);
		CollateralInterestParameters.CollateralInterestParametersBuilder setInterestHandlingParameters(CollateralInterestHandlingParameters interestHandlingParameters);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("postingParty"), CounterpartyRoleEnum.class, getPostingParty(), this);
			processor.processBasic(path.newSubPath("marginType"), CollateralMarginTypeEnum.class, getMarginType(), this);
			processor.processBasic(path.newSubPath("currency"), String.class, getCurrency(), this);
			processRosetta(path.newSubPath("interestCalculationParameters"), processor, CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder.class, getInterestCalculationParameters());
			processRosetta(path.newSubPath("interestCalculationFrequency"), processor, CalculationFrequency.CalculationFrequencyBuilder.class, getInterestCalculationFrequency());
			processRosetta(path.newSubPath("interestHandlingParameters"), processor, CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder.class, getInterestHandlingParameters());
		}
		

		CollateralInterestParameters.CollateralInterestParametersBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralInterestParameters  ***********************/
	class CollateralInterestParametersImpl implements CollateralInterestParameters {
		private final CounterpartyRoleEnum postingParty;
		private final CollateralMarginTypeEnum marginType;
		private final String currency;
		private final CollateralInterestCalculationParameters interestCalculationParameters;
		private final CalculationFrequency interestCalculationFrequency;
		private final CollateralInterestHandlingParameters interestHandlingParameters;
		
		protected CollateralInterestParametersImpl(CollateralInterestParameters.CollateralInterestParametersBuilder builder) {
			this.postingParty = builder.getPostingParty();
			this.marginType = builder.getMarginType();
			this.currency = builder.getCurrency();
			this.interestCalculationParameters = ofNullable(builder.getInterestCalculationParameters()).map(f->f.build()).orElse(null);
			this.interestCalculationFrequency = ofNullable(builder.getInterestCalculationFrequency()).map(f->f.build()).orElse(null);
			this.interestHandlingParameters = ofNullable(builder.getInterestHandlingParameters()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("postingParty")
		public CounterpartyRoleEnum getPostingParty() {
			return postingParty;
		}
		
		@Override
		@RosettaAttribute("marginType")
		public CollateralMarginTypeEnum getMarginType() {
			return marginType;
		}
		
		@Override
		@RosettaAttribute("currency")
		public String getCurrency() {
			return currency;
		}
		
		@Override
		@RosettaAttribute("interestCalculationParameters")
		public CollateralInterestCalculationParameters getInterestCalculationParameters() {
			return interestCalculationParameters;
		}
		
		@Override
		@RosettaAttribute("interestCalculationFrequency")
		public CalculationFrequency getInterestCalculationFrequency() {
			return interestCalculationFrequency;
		}
		
		@Override
		@RosettaAttribute("interestHandlingParameters")
		public CollateralInterestHandlingParameters getInterestHandlingParameters() {
			return interestHandlingParameters;
		}
		
		@Override
		public CollateralInterestParameters build() {
			return this;
		}
		
		@Override
		public CollateralInterestParameters.CollateralInterestParametersBuilder toBuilder() {
			CollateralInterestParameters.CollateralInterestParametersBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralInterestParameters.CollateralInterestParametersBuilder builder) {
			ofNullable(getPostingParty()).ifPresent(builder::setPostingParty);
			ofNullable(getMarginType()).ifPresent(builder::setMarginType);
			ofNullable(getCurrency()).ifPresent(builder::setCurrency);
			ofNullable(getInterestCalculationParameters()).ifPresent(builder::setInterestCalculationParameters);
			ofNullable(getInterestCalculationFrequency()).ifPresent(builder::setInterestCalculationFrequency);
			ofNullable(getInterestHandlingParameters()).ifPresent(builder::setInterestHandlingParameters);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralInterestParameters _that = getType().cast(o);
		
			if (!Objects.equals(postingParty, _that.getPostingParty())) return false;
			if (!Objects.equals(marginType, _that.getMarginType())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			if (!Objects.equals(interestCalculationParameters, _that.getInterestCalculationParameters())) return false;
			if (!Objects.equals(interestCalculationFrequency, _that.getInterestCalculationFrequency())) return false;
			if (!Objects.equals(interestHandlingParameters, _that.getInterestHandlingParameters())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (postingParty != null ? postingParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (marginType != null ? marginType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			_result = 31 * _result + (interestCalculationParameters != null ? interestCalculationParameters.hashCode() : 0);
			_result = 31 * _result + (interestCalculationFrequency != null ? interestCalculationFrequency.hashCode() : 0);
			_result = 31 * _result + (interestHandlingParameters != null ? interestHandlingParameters.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralInterestParameters {" +
				"postingParty=" + this.postingParty + ", " +
				"marginType=" + this.marginType + ", " +
				"currency=" + this.currency + ", " +
				"interestCalculationParameters=" + this.interestCalculationParameters + ", " +
				"interestCalculationFrequency=" + this.interestCalculationFrequency + ", " +
				"interestHandlingParameters=" + this.interestHandlingParameters +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralInterestParameters  ***********************/
	class CollateralInterestParametersBuilderImpl implements CollateralInterestParameters.CollateralInterestParametersBuilder {
	
		protected CounterpartyRoleEnum postingParty;
		protected CollateralMarginTypeEnum marginType;
		protected String currency;
		protected CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder interestCalculationParameters;
		protected CalculationFrequency.CalculationFrequencyBuilder interestCalculationFrequency;
		protected CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder interestHandlingParameters;
	
		public CollateralInterestParametersBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("postingParty")
		public CounterpartyRoleEnum getPostingParty() {
			return postingParty;
		}
		
		@Override
		@RosettaAttribute("marginType")
		public CollateralMarginTypeEnum getMarginType() {
			return marginType;
		}
		
		@Override
		@RosettaAttribute("currency")
		public String getCurrency() {
			return currency;
		}
		
		@Override
		@RosettaAttribute("interestCalculationParameters")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder getInterestCalculationParameters() {
			return interestCalculationParameters;
		}
		
		@Override
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder getOrCreateInterestCalculationParameters() {
			CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder result;
			if (interestCalculationParameters!=null) {
				result = interestCalculationParameters;
			}
			else {
				result = interestCalculationParameters = CollateralInterestCalculationParameters.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("interestCalculationFrequency")
		public CalculationFrequency.CalculationFrequencyBuilder getInterestCalculationFrequency() {
			return interestCalculationFrequency;
		}
		
		@Override
		public CalculationFrequency.CalculationFrequencyBuilder getOrCreateInterestCalculationFrequency() {
			CalculationFrequency.CalculationFrequencyBuilder result;
			if (interestCalculationFrequency!=null) {
				result = interestCalculationFrequency;
			}
			else {
				result = interestCalculationFrequency = CalculationFrequency.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("interestHandlingParameters")
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder getInterestHandlingParameters() {
			return interestHandlingParameters;
		}
		
		@Override
		public CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder getOrCreateInterestHandlingParameters() {
			CollateralInterestHandlingParameters.CollateralInterestHandlingParametersBuilder result;
			if (interestHandlingParameters!=null) {
				result = interestHandlingParameters;
			}
			else {
				result = interestHandlingParameters = CollateralInterestHandlingParameters.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("postingParty")
		public CollateralInterestParameters.CollateralInterestParametersBuilder setPostingParty(CounterpartyRoleEnum postingParty) {
			this.postingParty = postingParty==null?null:postingParty;
			return this;
		}
		@Override
		@RosettaAttribute("marginType")
		public CollateralInterestParameters.CollateralInterestParametersBuilder setMarginType(CollateralMarginTypeEnum marginType) {
			this.marginType = marginType==null?null:marginType;
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public CollateralInterestParameters.CollateralInterestParametersBuilder setCurrency(String currency) {
			this.currency = currency==null?null:currency;
			return this;
		}
		@Override
		@RosettaAttribute("interestCalculationParameters")
		public CollateralInterestParameters.CollateralInterestParametersBuilder setInterestCalculationParameters(CollateralInterestCalculationParameters interestCalculationParameters) {
			this.interestCalculationParameters = interestCalculationParameters==null?null:interestCalculationParameters.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("interestCalculationFrequency")
		public CollateralInterestParameters.CollateralInterestParametersBuilder setInterestCalculationFrequency(CalculationFrequency interestCalculationFrequency) {
			this.interestCalculationFrequency = interestCalculationFrequency==null?null:interestCalculationFrequency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("interestHandlingParameters")
		public CollateralInterestParameters.CollateralInterestParametersBuilder setInterestHandlingParameters(CollateralInterestHandlingParameters interestHandlingParameters) {
			this.interestHandlingParameters = interestHandlingParameters==null?null:interestHandlingParameters.toBuilder();
			return this;
		}
		
		@Override
		public CollateralInterestParameters build() {
			return new CollateralInterestParameters.CollateralInterestParametersImpl(this);
		}
		
		@Override
		public CollateralInterestParameters.CollateralInterestParametersBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralInterestParameters.CollateralInterestParametersBuilder prune() {
			if (interestCalculationParameters!=null && !interestCalculationParameters.prune().hasData()) interestCalculationParameters = null;
			if (interestCalculationFrequency!=null && !interestCalculationFrequency.prune().hasData()) interestCalculationFrequency = null;
			if (interestHandlingParameters!=null && !interestHandlingParameters.prune().hasData()) interestHandlingParameters = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPostingParty()!=null) return true;
			if (getMarginType()!=null) return true;
			if (getCurrency()!=null) return true;
			if (getInterestCalculationParameters()!=null && getInterestCalculationParameters().hasData()) return true;
			if (getInterestCalculationFrequency()!=null && getInterestCalculationFrequency().hasData()) return true;
			if (getInterestHandlingParameters()!=null && getInterestHandlingParameters().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralInterestParameters.CollateralInterestParametersBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralInterestParameters.CollateralInterestParametersBuilder o = (CollateralInterestParameters.CollateralInterestParametersBuilder) other;
			
			merger.mergeRosetta(getInterestCalculationParameters(), o.getInterestCalculationParameters(), this::setInterestCalculationParameters);
			merger.mergeRosetta(getInterestCalculationFrequency(), o.getInterestCalculationFrequency(), this::setInterestCalculationFrequency);
			merger.mergeRosetta(getInterestHandlingParameters(), o.getInterestHandlingParameters(), this::setInterestHandlingParameters);
			
			merger.mergeBasic(getPostingParty(), o.getPostingParty(), this::setPostingParty);
			merger.mergeBasic(getMarginType(), o.getMarginType(), this::setMarginType);
			merger.mergeBasic(getCurrency(), o.getCurrency(), this::setCurrency);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralInterestParameters _that = getType().cast(o);
		
			if (!Objects.equals(postingParty, _that.getPostingParty())) return false;
			if (!Objects.equals(marginType, _that.getMarginType())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			if (!Objects.equals(interestCalculationParameters, _that.getInterestCalculationParameters())) return false;
			if (!Objects.equals(interestCalculationFrequency, _that.getInterestCalculationFrequency())) return false;
			if (!Objects.equals(interestHandlingParameters, _that.getInterestHandlingParameters())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (postingParty != null ? postingParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (marginType != null ? marginType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			_result = 31 * _result + (interestCalculationParameters != null ? interestCalculationParameters.hashCode() : 0);
			_result = 31 * _result + (interestCalculationFrequency != null ? interestCalculationFrequency.hashCode() : 0);
			_result = 31 * _result + (interestHandlingParameters != null ? interestHandlingParameters.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralInterestParametersBuilder {" +
				"postingParty=" + this.postingParty + ", " +
				"marginType=" + this.marginType + ", " +
				"currency=" + this.currency + ", " +
				"interestCalculationParameters=" + this.interestCalculationParameters + ", " +
				"interestCalculationFrequency=" + this.interestCalculationFrequency + ", " +
				"interestHandlingParameters=" + this.interestHandlingParameters +
			'}';
		}
	}
}
