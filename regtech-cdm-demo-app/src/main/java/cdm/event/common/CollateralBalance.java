package cdm.event.common;

import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.event.common.CollateralBalance;
import cdm.event.common.CollateralBalance.CollateralBalanceBuilder;
import cdm.event.common.CollateralBalance.CollateralBalanceBuilderImpl;
import cdm.event.common.CollateralBalance.CollateralBalanceImpl;
import cdm.event.common.CollateralStatusEnum;
import cdm.event.common.HaircutIndicatorEnum;
import cdm.event.common.meta.CollateralBalanceMeta;
import cdm.observable.asset.Money;
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
 * Represents common attributes to define a collateral balance recorded by the principal as held or posted.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralBalance", builder=CollateralBalance.CollateralBalanceBuilderImpl.class, version="${project.version}")
public interface CollateralBalance extends RosettaModelObject {

	CollateralBalanceMeta metaData = new CollateralBalanceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines the collateral balance breakdown of settlement status.
	 */
	CollateralStatusEnum getCollateralBalanceStatus();
	/**
	 * Indicates if the collateral balance amount is based on pre or post haircut, if a haircut is associated with the collateral asset
	 */
	HaircutIndicatorEnum getHaircutIndicator();
	/**
	 * Specifies the collateral balance amount in base currency determined within a collateral legal agreement, or defined for reporting purposes.
	 */
	Money getAmountBaseCurrency();
	/**
	 * Specifies each of the parties in the collateral balance and its perspective with regards to the direction of the collateral balance, posted or received.
	 */
	PartyReferencePayerReceiver getPayerReceiver();

	/*********************** Build Methods  ***********************/
	CollateralBalance build();
	
	CollateralBalance.CollateralBalanceBuilder toBuilder();
	
	static CollateralBalance.CollateralBalanceBuilder builder() {
		return new CollateralBalance.CollateralBalanceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralBalance> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralBalance> getType() {
		return CollateralBalance.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("collateralBalanceStatus"), CollateralStatusEnum.class, getCollateralBalanceStatus(), this);
		processor.processBasic(path.newSubPath("haircutIndicator"), HaircutIndicatorEnum.class, getHaircutIndicator(), this);
		processRosetta(path.newSubPath("amountBaseCurrency"), processor, Money.class, getAmountBaseCurrency());
		processRosetta(path.newSubPath("payerReceiver"), processor, PartyReferencePayerReceiver.class, getPayerReceiver());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralBalanceBuilder extends CollateralBalance, RosettaModelObjectBuilder {
		Money.MoneyBuilder getOrCreateAmountBaseCurrency();
		Money.MoneyBuilder getAmountBaseCurrency();
		PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder getOrCreatePayerReceiver();
		PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder getPayerReceiver();
		CollateralBalance.CollateralBalanceBuilder setCollateralBalanceStatus(CollateralStatusEnum collateralBalanceStatus);
		CollateralBalance.CollateralBalanceBuilder setHaircutIndicator(HaircutIndicatorEnum haircutIndicator);
		CollateralBalance.CollateralBalanceBuilder setAmountBaseCurrency(Money amountBaseCurrency);
		CollateralBalance.CollateralBalanceBuilder setPayerReceiver(PartyReferencePayerReceiver payerReceiver);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("collateralBalanceStatus"), CollateralStatusEnum.class, getCollateralBalanceStatus(), this);
			processor.processBasic(path.newSubPath("haircutIndicator"), HaircutIndicatorEnum.class, getHaircutIndicator(), this);
			processRosetta(path.newSubPath("amountBaseCurrency"), processor, Money.MoneyBuilder.class, getAmountBaseCurrency());
			processRosetta(path.newSubPath("payerReceiver"), processor, PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder.class, getPayerReceiver());
		}
		

		CollateralBalance.CollateralBalanceBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralBalance  ***********************/
	class CollateralBalanceImpl implements CollateralBalance {
		private final CollateralStatusEnum collateralBalanceStatus;
		private final HaircutIndicatorEnum haircutIndicator;
		private final Money amountBaseCurrency;
		private final PartyReferencePayerReceiver payerReceiver;
		
		protected CollateralBalanceImpl(CollateralBalance.CollateralBalanceBuilder builder) {
			this.collateralBalanceStatus = builder.getCollateralBalanceStatus();
			this.haircutIndicator = builder.getHaircutIndicator();
			this.amountBaseCurrency = ofNullable(builder.getAmountBaseCurrency()).map(f->f.build()).orElse(null);
			this.payerReceiver = ofNullable(builder.getPayerReceiver()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("collateralBalanceStatus")
		public CollateralStatusEnum getCollateralBalanceStatus() {
			return collateralBalanceStatus;
		}
		
		@Override
		@RosettaAttribute("haircutIndicator")
		public HaircutIndicatorEnum getHaircutIndicator() {
			return haircutIndicator;
		}
		
		@Override
		@RosettaAttribute("amountBaseCurrency")
		public Money getAmountBaseCurrency() {
			return amountBaseCurrency;
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public PartyReferencePayerReceiver getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		public CollateralBalance build() {
			return this;
		}
		
		@Override
		public CollateralBalance.CollateralBalanceBuilder toBuilder() {
			CollateralBalance.CollateralBalanceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralBalance.CollateralBalanceBuilder builder) {
			ofNullable(getCollateralBalanceStatus()).ifPresent(builder::setCollateralBalanceStatus);
			ofNullable(getHaircutIndicator()).ifPresent(builder::setHaircutIndicator);
			ofNullable(getAmountBaseCurrency()).ifPresent(builder::setAmountBaseCurrency);
			ofNullable(getPayerReceiver()).ifPresent(builder::setPayerReceiver);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralBalance _that = getType().cast(o);
		
			if (!Objects.equals(collateralBalanceStatus, _that.getCollateralBalanceStatus())) return false;
			if (!Objects.equals(haircutIndicator, _that.getHaircutIndicator())) return false;
			if (!Objects.equals(amountBaseCurrency, _that.getAmountBaseCurrency())) return false;
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (collateralBalanceStatus != null ? collateralBalanceStatus.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (haircutIndicator != null ? haircutIndicator.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (amountBaseCurrency != null ? amountBaseCurrency.hashCode() : 0);
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralBalance {" +
				"collateralBalanceStatus=" + this.collateralBalanceStatus + ", " +
				"haircutIndicator=" + this.haircutIndicator + ", " +
				"amountBaseCurrency=" + this.amountBaseCurrency + ", " +
				"payerReceiver=" + this.payerReceiver +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralBalance  ***********************/
	class CollateralBalanceBuilderImpl implements CollateralBalance.CollateralBalanceBuilder {
	
		protected CollateralStatusEnum collateralBalanceStatus;
		protected HaircutIndicatorEnum haircutIndicator;
		protected Money.MoneyBuilder amountBaseCurrency;
		protected PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder payerReceiver;
	
		public CollateralBalanceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("collateralBalanceStatus")
		public CollateralStatusEnum getCollateralBalanceStatus() {
			return collateralBalanceStatus;
		}
		
		@Override
		@RosettaAttribute("haircutIndicator")
		public HaircutIndicatorEnum getHaircutIndicator() {
			return haircutIndicator;
		}
		
		@Override
		@RosettaAttribute("amountBaseCurrency")
		public Money.MoneyBuilder getAmountBaseCurrency() {
			return amountBaseCurrency;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateAmountBaseCurrency() {
			Money.MoneyBuilder result;
			if (amountBaseCurrency!=null) {
				result = amountBaseCurrency;
			}
			else {
				result = amountBaseCurrency = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("payerReceiver")
		public PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		public PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder getOrCreatePayerReceiver() {
			PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder result;
			if (payerReceiver!=null) {
				result = payerReceiver;
			}
			else {
				result = payerReceiver = PartyReferencePayerReceiver.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("collateralBalanceStatus")
		public CollateralBalance.CollateralBalanceBuilder setCollateralBalanceStatus(CollateralStatusEnum collateralBalanceStatus) {
			this.collateralBalanceStatus = collateralBalanceStatus==null?null:collateralBalanceStatus;
			return this;
		}
		@Override
		@RosettaAttribute("haircutIndicator")
		public CollateralBalance.CollateralBalanceBuilder setHaircutIndicator(HaircutIndicatorEnum haircutIndicator) {
			this.haircutIndicator = haircutIndicator==null?null:haircutIndicator;
			return this;
		}
		@Override
		@RosettaAttribute("amountBaseCurrency")
		public CollateralBalance.CollateralBalanceBuilder setAmountBaseCurrency(Money amountBaseCurrency) {
			this.amountBaseCurrency = amountBaseCurrency==null?null:amountBaseCurrency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("payerReceiver")
		public CollateralBalance.CollateralBalanceBuilder setPayerReceiver(PartyReferencePayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		
		@Override
		public CollateralBalance build() {
			return new CollateralBalance.CollateralBalanceImpl(this);
		}
		
		@Override
		public CollateralBalance.CollateralBalanceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralBalance.CollateralBalanceBuilder prune() {
			if (amountBaseCurrency!=null && !amountBaseCurrency.prune().hasData()) amountBaseCurrency = null;
			if (payerReceiver!=null && !payerReceiver.prune().hasData()) payerReceiver = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCollateralBalanceStatus()!=null) return true;
			if (getHaircutIndicator()!=null) return true;
			if (getAmountBaseCurrency()!=null && getAmountBaseCurrency().hasData()) return true;
			if (getPayerReceiver()!=null && getPayerReceiver().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralBalance.CollateralBalanceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralBalance.CollateralBalanceBuilder o = (CollateralBalance.CollateralBalanceBuilder) other;
			
			merger.mergeRosetta(getAmountBaseCurrency(), o.getAmountBaseCurrency(), this::setAmountBaseCurrency);
			merger.mergeRosetta(getPayerReceiver(), o.getPayerReceiver(), this::setPayerReceiver);
			
			merger.mergeBasic(getCollateralBalanceStatus(), o.getCollateralBalanceStatus(), this::setCollateralBalanceStatus);
			merger.mergeBasic(getHaircutIndicator(), o.getHaircutIndicator(), this::setHaircutIndicator);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralBalance _that = getType().cast(o);
		
			if (!Objects.equals(collateralBalanceStatus, _that.getCollateralBalanceStatus())) return false;
			if (!Objects.equals(haircutIndicator, _that.getHaircutIndicator())) return false;
			if (!Objects.equals(amountBaseCurrency, _that.getAmountBaseCurrency())) return false;
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (collateralBalanceStatus != null ? collateralBalanceStatus.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (haircutIndicator != null ? haircutIndicator.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (amountBaseCurrency != null ? amountBaseCurrency.hashCode() : 0);
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralBalanceBuilder {" +
				"collateralBalanceStatus=" + this.collateralBalanceStatus + ", " +
				"haircutIndicator=" + this.haircutIndicator + ", " +
				"amountBaseCurrency=" + this.amountBaseCurrency + ", " +
				"payerReceiver=" + this.payerReceiver +
			'}';
		}
	}
}
