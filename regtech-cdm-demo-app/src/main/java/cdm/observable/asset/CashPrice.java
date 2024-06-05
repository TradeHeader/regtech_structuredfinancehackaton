package cdm.observable.asset;

import cdm.observable.asset.CashPrice;
import cdm.observable.asset.CashPrice.CashPriceBuilder;
import cdm.observable.asset.CashPrice.CashPriceBuilderImpl;
import cdm.observable.asset.CashPrice.CashPriceImpl;
import cdm.observable.asset.CashPriceTypeEnum;
import cdm.observable.asset.FeeTypeEnum;
import cdm.observable.asset.PremiumExpression;
import cdm.observable.asset.meta.CashPriceMeta;
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
 * Specifies the nature of a cash price either as a fee type, cash price type, or premium expression.
 * @version ${project.version}
 */
@RosettaDataType(value="CashPrice", builder=CashPrice.CashPriceBuilderImpl.class, version="${project.version}")
public interface CashPrice extends RosettaModelObject {

	CashPriceMeta metaData = new CashPriceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the type of Cash Price.
	 */
	CashPriceTypeEnum getCashPriceType();
	/**
	 * Specifies a premium when expressed in a way other than an amount, and any required forward starting price definition.
	 */
	PremiumExpression getPremiumExpression();
	/**
	 * Specifies the event type associated with a fee.
	 */
	FeeTypeEnum getFeeType();

	/*********************** Build Methods  ***********************/
	CashPrice build();
	
	CashPrice.CashPriceBuilder toBuilder();
	
	static CashPrice.CashPriceBuilder builder() {
		return new CashPrice.CashPriceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CashPrice> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CashPrice> getType() {
		return CashPrice.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("cashPriceType"), CashPriceTypeEnum.class, getCashPriceType(), this);
		processRosetta(path.newSubPath("premiumExpression"), processor, PremiumExpression.class, getPremiumExpression());
		processor.processBasic(path.newSubPath("feeType"), FeeTypeEnum.class, getFeeType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CashPriceBuilder extends CashPrice, RosettaModelObjectBuilder {
		PremiumExpression.PremiumExpressionBuilder getOrCreatePremiumExpression();
		PremiumExpression.PremiumExpressionBuilder getPremiumExpression();
		CashPrice.CashPriceBuilder setCashPriceType(CashPriceTypeEnum cashPriceType);
		CashPrice.CashPriceBuilder setPremiumExpression(PremiumExpression premiumExpression);
		CashPrice.CashPriceBuilder setFeeType(FeeTypeEnum feeType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("cashPriceType"), CashPriceTypeEnum.class, getCashPriceType(), this);
			processRosetta(path.newSubPath("premiumExpression"), processor, PremiumExpression.PremiumExpressionBuilder.class, getPremiumExpression());
			processor.processBasic(path.newSubPath("feeType"), FeeTypeEnum.class, getFeeType(), this);
		}
		

		CashPrice.CashPriceBuilder prune();
	}

	/*********************** Immutable Implementation of CashPrice  ***********************/
	class CashPriceImpl implements CashPrice {
		private final CashPriceTypeEnum cashPriceType;
		private final PremiumExpression premiumExpression;
		private final FeeTypeEnum feeType;
		
		protected CashPriceImpl(CashPrice.CashPriceBuilder builder) {
			this.cashPriceType = builder.getCashPriceType();
			this.premiumExpression = ofNullable(builder.getPremiumExpression()).map(f->f.build()).orElse(null);
			this.feeType = builder.getFeeType();
		}
		
		@Override
		@RosettaAttribute("cashPriceType")
		public CashPriceTypeEnum getCashPriceType() {
			return cashPriceType;
		}
		
		@Override
		@RosettaAttribute("premiumExpression")
		public PremiumExpression getPremiumExpression() {
			return premiumExpression;
		}
		
		@Override
		@RosettaAttribute("feeType")
		public FeeTypeEnum getFeeType() {
			return feeType;
		}
		
		@Override
		public CashPrice build() {
			return this;
		}
		
		@Override
		public CashPrice.CashPriceBuilder toBuilder() {
			CashPrice.CashPriceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CashPrice.CashPriceBuilder builder) {
			ofNullable(getCashPriceType()).ifPresent(builder::setCashPriceType);
			ofNullable(getPremiumExpression()).ifPresent(builder::setPremiumExpression);
			ofNullable(getFeeType()).ifPresent(builder::setFeeType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashPrice _that = getType().cast(o);
		
			if (!Objects.equals(cashPriceType, _that.getCashPriceType())) return false;
			if (!Objects.equals(premiumExpression, _that.getPremiumExpression())) return false;
			if (!Objects.equals(feeType, _that.getFeeType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cashPriceType != null ? cashPriceType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (premiumExpression != null ? premiumExpression.hashCode() : 0);
			_result = 31 * _result + (feeType != null ? feeType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashPrice {" +
				"cashPriceType=" + this.cashPriceType + ", " +
				"premiumExpression=" + this.premiumExpression + ", " +
				"feeType=" + this.feeType +
			'}';
		}
	}

	/*********************** Builder Implementation of CashPrice  ***********************/
	class CashPriceBuilderImpl implements CashPrice.CashPriceBuilder {
	
		protected CashPriceTypeEnum cashPriceType;
		protected PremiumExpression.PremiumExpressionBuilder premiumExpression;
		protected FeeTypeEnum feeType;
	
		public CashPriceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("cashPriceType")
		public CashPriceTypeEnum getCashPriceType() {
			return cashPriceType;
		}
		
		@Override
		@RosettaAttribute("premiumExpression")
		public PremiumExpression.PremiumExpressionBuilder getPremiumExpression() {
			return premiumExpression;
		}
		
		@Override
		public PremiumExpression.PremiumExpressionBuilder getOrCreatePremiumExpression() {
			PremiumExpression.PremiumExpressionBuilder result;
			if (premiumExpression!=null) {
				result = premiumExpression;
			}
			else {
				result = premiumExpression = PremiumExpression.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("feeType")
		public FeeTypeEnum getFeeType() {
			return feeType;
		}
		
	
		@Override
		@RosettaAttribute("cashPriceType")
		public CashPrice.CashPriceBuilder setCashPriceType(CashPriceTypeEnum cashPriceType) {
			this.cashPriceType = cashPriceType==null?null:cashPriceType;
			return this;
		}
		@Override
		@RosettaAttribute("premiumExpression")
		public CashPrice.CashPriceBuilder setPremiumExpression(PremiumExpression premiumExpression) {
			this.premiumExpression = premiumExpression==null?null:premiumExpression.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("feeType")
		public CashPrice.CashPriceBuilder setFeeType(FeeTypeEnum feeType) {
			this.feeType = feeType==null?null:feeType;
			return this;
		}
		
		@Override
		public CashPrice build() {
			return new CashPrice.CashPriceImpl(this);
		}
		
		@Override
		public CashPrice.CashPriceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashPrice.CashPriceBuilder prune() {
			if (premiumExpression!=null && !premiumExpression.prune().hasData()) premiumExpression = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCashPriceType()!=null) return true;
			if (getPremiumExpression()!=null && getPremiumExpression().hasData()) return true;
			if (getFeeType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashPrice.CashPriceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CashPrice.CashPriceBuilder o = (CashPrice.CashPriceBuilder) other;
			
			merger.mergeRosetta(getPremiumExpression(), o.getPremiumExpression(), this::setPremiumExpression);
			
			merger.mergeBasic(getCashPriceType(), o.getCashPriceType(), this::setCashPriceType);
			merger.mergeBasic(getFeeType(), o.getFeeType(), this::setFeeType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashPrice _that = getType().cast(o);
		
			if (!Objects.equals(cashPriceType, _that.getCashPriceType())) return false;
			if (!Objects.equals(premiumExpression, _that.getPremiumExpression())) return false;
			if (!Objects.equals(feeType, _that.getFeeType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cashPriceType != null ? cashPriceType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (premiumExpression != null ? premiumExpression.hashCode() : 0);
			_result = 31 * _result + (feeType != null ? feeType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashPriceBuilder {" +
				"cashPriceType=" + this.cashPriceType + ", " +
				"premiumExpression=" + this.premiumExpression + ", " +
				"feeType=" + this.feeType +
			'}';
		}
	}
}
