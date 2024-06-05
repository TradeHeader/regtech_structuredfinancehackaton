package cdm.product.template;

import cdm.base.staticdata.party.BuyerSeller;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.common.schedule.ObservationTerms;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.OptionExercise;
import cdm.product.template.OptionFeature;
import cdm.product.template.OptionPayout;
import cdm.product.template.OptionPayout.OptionPayoutBuilder;
import cdm.product.template.OptionPayout.OptionPayoutBuilderImpl;
import cdm.product.template.OptionPayout.OptionPayoutImpl;
import cdm.product.template.OptionTypeEnum;
import cdm.product.template.Product;
import cdm.product.template.meta.OptionPayoutMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 *  The option payout specification terms. The associated globalKey denotes the ability to associate a hash value to the respective OptionPayout instantiation for the purpose of model cross-referencing, in support of functionality such as the event effect and the lineage.
 * @version ${project.version}
 */
@RosettaDataType(value="OptionPayout", builder=OptionPayout.OptionPayoutBuilderImpl.class, version="${project.version}")
public interface OptionPayout extends PayoutBase, GlobalKey {

	OptionPayoutMeta metaData = new OptionPayoutMeta();

	/*********************** Getter Methods  ***********************/
	BuyerSeller getBuyerSeller();
	/**
	 * The type of option transaction. From a usage standpoint, put/call is the default option type, while payer/receiver indicator is used for options on index credit default swaps, consistently with the industry practice. Straddle is used for the case of straddle strategy, that combine a call and a put with the same strike.
	 */
	OptionTypeEnum getOptionType();
	/**
	 * The option feature, such as quanto, Asian, barrier, knock.
	 */
	OptionFeature getFeature();
	/**
	 * The terms for exercising the option, which include the option style (e.g. American style option), the exercise procedure (e.g. manual exercise) and the settlement terms (e.g. physical vs. cash).
	 */
	OptionExercise getExerciseTerms();
	/**
	 * The product underlying the option, which can be of any type including ContractualProduct or Security.
	 */
	Product getUnderlier();
	/**
	 * Class containing terms that are associated with observing a price/benchmark/index across either single or multple observations. To be used for option contracts that reference a benchmark price.
	 */
	ObservationTerms getObservationTerms();
	/**
	 * Allows the full representation of a payout by defining a set of schedule periods. It supports standard schedule customization by expressing all the dates, quantities, and pricing data in a non-parametric way.
	 */
	CalculationSchedule getSchedule();
	/**
	 * Contains the information relative to the delivery of the asset.
	 */
	AssetDeliveryInformation getDelivery();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	OptionPayout build();
	
	OptionPayout.OptionPayoutBuilder toBuilder();
	
	static OptionPayout.OptionPayoutBuilder builder() {
		return new OptionPayout.OptionPayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OptionPayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OptionPayout> getType() {
		return OptionPayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("buyerSeller"), processor, BuyerSeller.class, getBuyerSeller());
		processor.processBasic(path.newSubPath("optionType"), OptionTypeEnum.class, getOptionType(), this);
		processRosetta(path.newSubPath("feature"), processor, OptionFeature.class, getFeature());
		processRosetta(path.newSubPath("exerciseTerms"), processor, OptionExercise.class, getExerciseTerms());
		processRosetta(path.newSubPath("underlier"), processor, Product.class, getUnderlier());
		processRosetta(path.newSubPath("observationTerms"), processor, ObservationTerms.class, getObservationTerms());
		processRosetta(path.newSubPath("schedule"), processor, CalculationSchedule.class, getSchedule());
		processRosetta(path.newSubPath("delivery"), processor, AssetDeliveryInformation.class, getDelivery());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface OptionPayoutBuilder extends OptionPayout, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		BuyerSeller.BuyerSellerBuilder getOrCreateBuyerSeller();
		BuyerSeller.BuyerSellerBuilder getBuyerSeller();
		OptionFeature.OptionFeatureBuilder getOrCreateFeature();
		OptionFeature.OptionFeatureBuilder getFeature();
		OptionExercise.OptionExerciseBuilder getOrCreateExerciseTerms();
		OptionExercise.OptionExerciseBuilder getExerciseTerms();
		Product.ProductBuilder getOrCreateUnderlier();
		Product.ProductBuilder getUnderlier();
		ObservationTerms.ObservationTermsBuilder getOrCreateObservationTerms();
		ObservationTerms.ObservationTermsBuilder getObservationTerms();
		CalculationSchedule.CalculationScheduleBuilder getOrCreateSchedule();
		CalculationSchedule.CalculationScheduleBuilder getSchedule();
		AssetDeliveryInformation.AssetDeliveryInformationBuilder getOrCreateDelivery();
		AssetDeliveryInformation.AssetDeliveryInformationBuilder getDelivery();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		OptionPayout.OptionPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver);
		OptionPayout.OptionPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		OptionPayout.OptionPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		OptionPayout.OptionPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms);
		OptionPayout.OptionPayoutBuilder setBuyerSeller(BuyerSeller buyerSeller);
		OptionPayout.OptionPayoutBuilder setOptionType(OptionTypeEnum optionType);
		OptionPayout.OptionPayoutBuilder setFeature(OptionFeature feature);
		OptionPayout.OptionPayoutBuilder setExerciseTerms(OptionExercise exerciseTerms);
		OptionPayout.OptionPayoutBuilder setUnderlier(Product underlier);
		OptionPayout.OptionPayoutBuilder setObservationTerms(ObservationTerms observationTerms);
		OptionPayout.OptionPayoutBuilder setSchedule(CalculationSchedule schedule);
		OptionPayout.OptionPayoutBuilder setDelivery(AssetDeliveryInformation delivery);
		OptionPayout.OptionPayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("buyerSeller"), processor, BuyerSeller.BuyerSellerBuilder.class, getBuyerSeller());
			processor.processBasic(path.newSubPath("optionType"), OptionTypeEnum.class, getOptionType(), this);
			processRosetta(path.newSubPath("feature"), processor, OptionFeature.OptionFeatureBuilder.class, getFeature());
			processRosetta(path.newSubPath("exerciseTerms"), processor, OptionExercise.OptionExerciseBuilder.class, getExerciseTerms());
			processRosetta(path.newSubPath("underlier"), processor, Product.ProductBuilder.class, getUnderlier());
			processRosetta(path.newSubPath("observationTerms"), processor, ObservationTerms.ObservationTermsBuilder.class, getObservationTerms());
			processRosetta(path.newSubPath("schedule"), processor, CalculationSchedule.CalculationScheduleBuilder.class, getSchedule());
			processRosetta(path.newSubPath("delivery"), processor, AssetDeliveryInformation.AssetDeliveryInformationBuilder.class, getDelivery());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		OptionPayout.OptionPayoutBuilder prune();
	}

	/*********************** Immutable Implementation of OptionPayout  ***********************/
	class OptionPayoutImpl extends PayoutBase.PayoutBaseImpl implements OptionPayout {
		private final BuyerSeller buyerSeller;
		private final OptionTypeEnum optionType;
		private final OptionFeature feature;
		private final OptionExercise exerciseTerms;
		private final Product underlier;
		private final ObservationTerms observationTerms;
		private final CalculationSchedule schedule;
		private final AssetDeliveryInformation delivery;
		private final MetaFields meta;
		
		protected OptionPayoutImpl(OptionPayout.OptionPayoutBuilder builder) {
			super(builder);
			this.buyerSeller = ofNullable(builder.getBuyerSeller()).map(f->f.build()).orElse(null);
			this.optionType = builder.getOptionType();
			this.feature = ofNullable(builder.getFeature()).map(f->f.build()).orElse(null);
			this.exerciseTerms = ofNullable(builder.getExerciseTerms()).map(f->f.build()).orElse(null);
			this.underlier = ofNullable(builder.getUnderlier()).map(f->f.build()).orElse(null);
			this.observationTerms = ofNullable(builder.getObservationTerms()).map(f->f.build()).orElse(null);
			this.schedule = ofNullable(builder.getSchedule()).map(f->f.build()).orElse(null);
			this.delivery = ofNullable(builder.getDelivery()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("buyerSeller")
		public BuyerSeller getBuyerSeller() {
			return buyerSeller;
		}
		
		@Override
		@RosettaAttribute("optionType")
		public OptionTypeEnum getOptionType() {
			return optionType;
		}
		
		@Override
		@RosettaAttribute("feature")
		public OptionFeature getFeature() {
			return feature;
		}
		
		@Override
		@RosettaAttribute("exerciseTerms")
		public OptionExercise getExerciseTerms() {
			return exerciseTerms;
		}
		
		@Override
		@RosettaAttribute("underlier")
		public Product getUnderlier() {
			return underlier;
		}
		
		@Override
		@RosettaAttribute("observationTerms")
		public ObservationTerms getObservationTerms() {
			return observationTerms;
		}
		
		@Override
		@RosettaAttribute("schedule")
		public CalculationSchedule getSchedule() {
			return schedule;
		}
		
		@Override
		@RosettaAttribute("delivery")
		public AssetDeliveryInformation getDelivery() {
			return delivery;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public OptionPayout build() {
			return this;
		}
		
		@Override
		public OptionPayout.OptionPayoutBuilder toBuilder() {
			OptionPayout.OptionPayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OptionPayout.OptionPayoutBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getBuyerSeller()).ifPresent(builder::setBuyerSeller);
			ofNullable(getOptionType()).ifPresent(builder::setOptionType);
			ofNullable(getFeature()).ifPresent(builder::setFeature);
			ofNullable(getExerciseTerms()).ifPresent(builder::setExerciseTerms);
			ofNullable(getUnderlier()).ifPresent(builder::setUnderlier);
			ofNullable(getObservationTerms()).ifPresent(builder::setObservationTerms);
			ofNullable(getSchedule()).ifPresent(builder::setSchedule);
			ofNullable(getDelivery()).ifPresent(builder::setDelivery);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			OptionPayout _that = getType().cast(o);
		
			if (!Objects.equals(buyerSeller, _that.getBuyerSeller())) return false;
			if (!Objects.equals(optionType, _that.getOptionType())) return false;
			if (!Objects.equals(feature, _that.getFeature())) return false;
			if (!Objects.equals(exerciseTerms, _that.getExerciseTerms())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!Objects.equals(observationTerms, _that.getObservationTerms())) return false;
			if (!Objects.equals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(delivery, _that.getDelivery())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (buyerSeller != null ? buyerSeller.hashCode() : 0);
			_result = 31 * _result + (optionType != null ? optionType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (feature != null ? feature.hashCode() : 0);
			_result = 31 * _result + (exerciseTerms != null ? exerciseTerms.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (observationTerms != null ? observationTerms.hashCode() : 0);
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (delivery != null ? delivery.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionPayout {" +
				"buyerSeller=" + this.buyerSeller + ", " +
				"optionType=" + this.optionType + ", " +
				"feature=" + this.feature + ", " +
				"exerciseTerms=" + this.exerciseTerms + ", " +
				"underlier=" + this.underlier + ", " +
				"observationTerms=" + this.observationTerms + ", " +
				"schedule=" + this.schedule + ", " +
				"delivery=" + this.delivery + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of OptionPayout  ***********************/
	class OptionPayoutBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements OptionPayout.OptionPayoutBuilder, GlobalKeyBuilder {
	
		protected BuyerSeller.BuyerSellerBuilder buyerSeller;
		protected OptionTypeEnum optionType;
		protected OptionFeature.OptionFeatureBuilder feature;
		protected OptionExercise.OptionExerciseBuilder exerciseTerms;
		protected Product.ProductBuilder underlier;
		protected ObservationTerms.ObservationTermsBuilder observationTerms;
		protected CalculationSchedule.CalculationScheduleBuilder schedule;
		protected AssetDeliveryInformation.AssetDeliveryInformationBuilder delivery;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public OptionPayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("buyerSeller")
		public BuyerSeller.BuyerSellerBuilder getBuyerSeller() {
			return buyerSeller;
		}
		
		@Override
		public BuyerSeller.BuyerSellerBuilder getOrCreateBuyerSeller() {
			BuyerSeller.BuyerSellerBuilder result;
			if (buyerSeller!=null) {
				result = buyerSeller;
			}
			else {
				result = buyerSeller = BuyerSeller.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("optionType")
		public OptionTypeEnum getOptionType() {
			return optionType;
		}
		
		@Override
		@RosettaAttribute("feature")
		public OptionFeature.OptionFeatureBuilder getFeature() {
			return feature;
		}
		
		@Override
		public OptionFeature.OptionFeatureBuilder getOrCreateFeature() {
			OptionFeature.OptionFeatureBuilder result;
			if (feature!=null) {
				result = feature;
			}
			else {
				result = feature = OptionFeature.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exerciseTerms")
		public OptionExercise.OptionExerciseBuilder getExerciseTerms() {
			return exerciseTerms;
		}
		
		@Override
		public OptionExercise.OptionExerciseBuilder getOrCreateExerciseTerms() {
			OptionExercise.OptionExerciseBuilder result;
			if (exerciseTerms!=null) {
				result = exerciseTerms;
			}
			else {
				result = exerciseTerms = OptionExercise.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("underlier")
		public Product.ProductBuilder getUnderlier() {
			return underlier;
		}
		
		@Override
		public Product.ProductBuilder getOrCreateUnderlier() {
			Product.ProductBuilder result;
			if (underlier!=null) {
				result = underlier;
			}
			else {
				result = underlier = Product.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observationTerms")
		public ObservationTerms.ObservationTermsBuilder getObservationTerms() {
			return observationTerms;
		}
		
		@Override
		public ObservationTerms.ObservationTermsBuilder getOrCreateObservationTerms() {
			ObservationTerms.ObservationTermsBuilder result;
			if (observationTerms!=null) {
				result = observationTerms;
			}
			else {
				result = observationTerms = ObservationTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("schedule")
		public CalculationSchedule.CalculationScheduleBuilder getSchedule() {
			return schedule;
		}
		
		@Override
		public CalculationSchedule.CalculationScheduleBuilder getOrCreateSchedule() {
			CalculationSchedule.CalculationScheduleBuilder result;
			if (schedule!=null) {
				result = schedule;
			}
			else {
				result = schedule = CalculationSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("delivery")
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder getDelivery() {
			return delivery;
		}
		
		@Override
		public AssetDeliveryInformation.AssetDeliveryInformationBuilder getOrCreateDelivery() {
			AssetDeliveryInformation.AssetDeliveryInformationBuilder result;
			if (delivery!=null) {
				result = delivery;
			}
			else {
				result = delivery = AssetDeliveryInformation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("payerReceiver")
		public OptionPayout.OptionPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public OptionPayout.OptionPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public OptionPayout.OptionPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public OptionPayout.OptionPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("buyerSeller")
		public OptionPayout.OptionPayoutBuilder setBuyerSeller(BuyerSeller buyerSeller) {
			this.buyerSeller = buyerSeller==null?null:buyerSeller.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("optionType")
		public OptionPayout.OptionPayoutBuilder setOptionType(OptionTypeEnum optionType) {
			this.optionType = optionType==null?null:optionType;
			return this;
		}
		@Override
		@RosettaAttribute("feature")
		public OptionPayout.OptionPayoutBuilder setFeature(OptionFeature feature) {
			this.feature = feature==null?null:feature.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exerciseTerms")
		public OptionPayout.OptionPayoutBuilder setExerciseTerms(OptionExercise exerciseTerms) {
			this.exerciseTerms = exerciseTerms==null?null:exerciseTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("underlier")
		public OptionPayout.OptionPayoutBuilder setUnderlier(Product underlier) {
			this.underlier = underlier==null?null:underlier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observationTerms")
		public OptionPayout.OptionPayoutBuilder setObservationTerms(ObservationTerms observationTerms) {
			this.observationTerms = observationTerms==null?null:observationTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("schedule")
		public OptionPayout.OptionPayoutBuilder setSchedule(CalculationSchedule schedule) {
			this.schedule = schedule==null?null:schedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("delivery")
		public OptionPayout.OptionPayoutBuilder setDelivery(AssetDeliveryInformation delivery) {
			this.delivery = delivery==null?null:delivery.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public OptionPayout.OptionPayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public OptionPayout build() {
			return new OptionPayout.OptionPayoutImpl(this);
		}
		
		@Override
		public OptionPayout.OptionPayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionPayout.OptionPayoutBuilder prune() {
			super.prune();
			if (buyerSeller!=null && !buyerSeller.prune().hasData()) buyerSeller = null;
			if (feature!=null && !feature.prune().hasData()) feature = null;
			if (exerciseTerms!=null && !exerciseTerms.prune().hasData()) exerciseTerms = null;
			if (underlier!=null && !underlier.prune().hasData()) underlier = null;
			if (observationTerms!=null && !observationTerms.prune().hasData()) observationTerms = null;
			if (schedule!=null && !schedule.prune().hasData()) schedule = null;
			if (delivery!=null && !delivery.prune().hasData()) delivery = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getBuyerSeller()!=null && getBuyerSeller().hasData()) return true;
			if (getOptionType()!=null) return true;
			if (getFeature()!=null && getFeature().hasData()) return true;
			if (getExerciseTerms()!=null && getExerciseTerms().hasData()) return true;
			if (getUnderlier()!=null && getUnderlier().hasData()) return true;
			if (getObservationTerms()!=null && getObservationTerms().hasData()) return true;
			if (getSchedule()!=null && getSchedule().hasData()) return true;
			if (getDelivery()!=null && getDelivery().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionPayout.OptionPayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			OptionPayout.OptionPayoutBuilder o = (OptionPayout.OptionPayoutBuilder) other;
			
			merger.mergeRosetta(getBuyerSeller(), o.getBuyerSeller(), this::setBuyerSeller);
			merger.mergeRosetta(getFeature(), o.getFeature(), this::setFeature);
			merger.mergeRosetta(getExerciseTerms(), o.getExerciseTerms(), this::setExerciseTerms);
			merger.mergeRosetta(getUnderlier(), o.getUnderlier(), this::setUnderlier);
			merger.mergeRosetta(getObservationTerms(), o.getObservationTerms(), this::setObservationTerms);
			merger.mergeRosetta(getSchedule(), o.getSchedule(), this::setSchedule);
			merger.mergeRosetta(getDelivery(), o.getDelivery(), this::setDelivery);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getOptionType(), o.getOptionType(), this::setOptionType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			OptionPayout _that = getType().cast(o);
		
			if (!Objects.equals(buyerSeller, _that.getBuyerSeller())) return false;
			if (!Objects.equals(optionType, _that.getOptionType())) return false;
			if (!Objects.equals(feature, _that.getFeature())) return false;
			if (!Objects.equals(exerciseTerms, _that.getExerciseTerms())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!Objects.equals(observationTerms, _that.getObservationTerms())) return false;
			if (!Objects.equals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(delivery, _that.getDelivery())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (buyerSeller != null ? buyerSeller.hashCode() : 0);
			_result = 31 * _result + (optionType != null ? optionType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (feature != null ? feature.hashCode() : 0);
			_result = 31 * _result + (exerciseTerms != null ? exerciseTerms.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (observationTerms != null ? observationTerms.hashCode() : 0);
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (delivery != null ? delivery.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionPayoutBuilder {" +
				"buyerSeller=" + this.buyerSeller + ", " +
				"optionType=" + this.optionType + ", " +
				"feature=" + this.feature + ", " +
				"exerciseTerms=" + this.exerciseTerms + ", " +
				"underlier=" + this.underlier + ", " +
				"observationTerms=" + this.observationTerms + ", " +
				"schedule=" + this.schedule + ", " +
				"delivery=" + this.delivery + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
