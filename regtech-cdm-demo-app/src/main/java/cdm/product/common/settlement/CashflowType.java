package cdm.product.common.settlement;

import cdm.observable.asset.CashPrice;
import cdm.observable.asset.PriceExpressionEnum;
import cdm.product.common.settlement.CashflowType;
import cdm.product.common.settlement.CashflowType.CashflowTypeBuilder;
import cdm.product.common.settlement.CashflowType.CashflowTypeBuilderImpl;
import cdm.product.common.settlement.CashflowType.CashflowTypeImpl;
import cdm.product.common.settlement.ScheduledTransferEnum;
import cdm.product.common.settlement.meta.CashflowTypeMeta;
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
 * Characterises the type of cashflow, which can result from either a scheduled or a non-scheduled lifecycle event.
 * @version ${project.version}
 */
@RosettaDataType(value="CashflowType", builder=CashflowType.CashflowTypeBuilderImpl.class, version="${project.version}")
public interface CashflowType extends RosettaModelObject {

	CashflowTypeMeta metaData = new CashflowTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Type of cashflow corresponding to a scheduled event.
	 */
	ScheduledTransferEnum getCashflowType();
	/**
	 * Type of cashflow corresponding to a non-scheduled event, where a price must be agreed between the parties.
	 */
	CashPrice getCashPrice();
	PriceExpressionEnum getPriceExpression();

	/*********************** Build Methods  ***********************/
	CashflowType build();
	
	CashflowType.CashflowTypeBuilder toBuilder();
	
	static CashflowType.CashflowTypeBuilder builder() {
		return new CashflowType.CashflowTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CashflowType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CashflowType> getType() {
		return CashflowType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("cashflowType"), ScheduledTransferEnum.class, getCashflowType(), this);
		processRosetta(path.newSubPath("cashPrice"), processor, CashPrice.class, getCashPrice());
		processor.processBasic(path.newSubPath("priceExpression"), PriceExpressionEnum.class, getPriceExpression(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CashflowTypeBuilder extends CashflowType, RosettaModelObjectBuilder {
		CashPrice.CashPriceBuilder getOrCreateCashPrice();
		CashPrice.CashPriceBuilder getCashPrice();
		CashflowType.CashflowTypeBuilder setCashflowType(ScheduledTransferEnum cashflowType);
		CashflowType.CashflowTypeBuilder setCashPrice(CashPrice cashPrice);
		CashflowType.CashflowTypeBuilder setPriceExpression(PriceExpressionEnum priceExpression);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("cashflowType"), ScheduledTransferEnum.class, getCashflowType(), this);
			processRosetta(path.newSubPath("cashPrice"), processor, CashPrice.CashPriceBuilder.class, getCashPrice());
			processor.processBasic(path.newSubPath("priceExpression"), PriceExpressionEnum.class, getPriceExpression(), this);
		}
		

		CashflowType.CashflowTypeBuilder prune();
	}

	/*********************** Immutable Implementation of CashflowType  ***********************/
	class CashflowTypeImpl implements CashflowType {
		private final ScheduledTransferEnum cashflowType;
		private final CashPrice cashPrice;
		private final PriceExpressionEnum priceExpression;
		
		protected CashflowTypeImpl(CashflowType.CashflowTypeBuilder builder) {
			this.cashflowType = builder.getCashflowType();
			this.cashPrice = ofNullable(builder.getCashPrice()).map(f->f.build()).orElse(null);
			this.priceExpression = builder.getPriceExpression();
		}
		
		@Override
		@RosettaAttribute("cashflowType")
		public ScheduledTransferEnum getCashflowType() {
			return cashflowType;
		}
		
		@Override
		@RosettaAttribute("cashPrice")
		public CashPrice getCashPrice() {
			return cashPrice;
		}
		
		@Override
		@RosettaAttribute("priceExpression")
		public PriceExpressionEnum getPriceExpression() {
			return priceExpression;
		}
		
		@Override
		public CashflowType build() {
			return this;
		}
		
		@Override
		public CashflowType.CashflowTypeBuilder toBuilder() {
			CashflowType.CashflowTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CashflowType.CashflowTypeBuilder builder) {
			ofNullable(getCashflowType()).ifPresent(builder::setCashflowType);
			ofNullable(getCashPrice()).ifPresent(builder::setCashPrice);
			ofNullable(getPriceExpression()).ifPresent(builder::setPriceExpression);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashflowType _that = getType().cast(o);
		
			if (!Objects.equals(cashflowType, _that.getCashflowType())) return false;
			if (!Objects.equals(cashPrice, _that.getCashPrice())) return false;
			if (!Objects.equals(priceExpression, _that.getPriceExpression())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cashflowType != null ? cashflowType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (cashPrice != null ? cashPrice.hashCode() : 0);
			_result = 31 * _result + (priceExpression != null ? priceExpression.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashflowType {" +
				"cashflowType=" + this.cashflowType + ", " +
				"cashPrice=" + this.cashPrice + ", " +
				"priceExpression=" + this.priceExpression +
			'}';
		}
	}

	/*********************** Builder Implementation of CashflowType  ***********************/
	class CashflowTypeBuilderImpl implements CashflowType.CashflowTypeBuilder {
	
		protected ScheduledTransferEnum cashflowType;
		protected CashPrice.CashPriceBuilder cashPrice;
		protected PriceExpressionEnum priceExpression;
	
		public CashflowTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("cashflowType")
		public ScheduledTransferEnum getCashflowType() {
			return cashflowType;
		}
		
		@Override
		@RosettaAttribute("cashPrice")
		public CashPrice.CashPriceBuilder getCashPrice() {
			return cashPrice;
		}
		
		@Override
		public CashPrice.CashPriceBuilder getOrCreateCashPrice() {
			CashPrice.CashPriceBuilder result;
			if (cashPrice!=null) {
				result = cashPrice;
			}
			else {
				result = cashPrice = CashPrice.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("priceExpression")
		public PriceExpressionEnum getPriceExpression() {
			return priceExpression;
		}
		
	
		@Override
		@RosettaAttribute("cashflowType")
		public CashflowType.CashflowTypeBuilder setCashflowType(ScheduledTransferEnum cashflowType) {
			this.cashflowType = cashflowType==null?null:cashflowType;
			return this;
		}
		@Override
		@RosettaAttribute("cashPrice")
		public CashflowType.CashflowTypeBuilder setCashPrice(CashPrice cashPrice) {
			this.cashPrice = cashPrice==null?null:cashPrice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceExpression")
		public CashflowType.CashflowTypeBuilder setPriceExpression(PriceExpressionEnum priceExpression) {
			this.priceExpression = priceExpression==null?null:priceExpression;
			return this;
		}
		
		@Override
		public CashflowType build() {
			return new CashflowType.CashflowTypeImpl(this);
		}
		
		@Override
		public CashflowType.CashflowTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashflowType.CashflowTypeBuilder prune() {
			if (cashPrice!=null && !cashPrice.prune().hasData()) cashPrice = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCashflowType()!=null) return true;
			if (getCashPrice()!=null && getCashPrice().hasData()) return true;
			if (getPriceExpression()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CashflowType.CashflowTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CashflowType.CashflowTypeBuilder o = (CashflowType.CashflowTypeBuilder) other;
			
			merger.mergeRosetta(getCashPrice(), o.getCashPrice(), this::setCashPrice);
			
			merger.mergeBasic(getCashflowType(), o.getCashflowType(), this::setCashflowType);
			merger.mergeBasic(getPriceExpression(), o.getPriceExpression(), this::setPriceExpression);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CashflowType _that = getType().cast(o);
		
			if (!Objects.equals(cashflowType, _that.getCashflowType())) return false;
			if (!Objects.equals(cashPrice, _that.getCashPrice())) return false;
			if (!Objects.equals(priceExpression, _that.getPriceExpression())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (cashflowType != null ? cashflowType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (cashPrice != null ? cashPrice.hashCode() : 0);
			_result = 31 * _result + (priceExpression != null ? priceExpression.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CashflowTypeBuilder {" +
				"cashflowType=" + this.cashflowType + ", " +
				"cashPrice=" + this.cashPrice + ", " +
				"priceExpression=" + this.priceExpression +
			'}';
		}
	}
}
