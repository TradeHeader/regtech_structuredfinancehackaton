package cdm.product.template;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.AssetLeg;
import cdm.product.template.AssetPayout;
import cdm.product.template.AssetPayout.AssetPayoutBuilder;
import cdm.product.template.AssetPayout.AssetPayoutBuilderImpl;
import cdm.product.template.AssetPayout.AssetPayoutImpl;
import cdm.product.template.DividendTerms;
import cdm.product.template.Duration;
import cdm.product.template.Product;
import cdm.product.template.meta.AssetPayoutMeta;
import com.google.common.collect.ImmutableList;
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
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Security finance payout specification in case the product payout involves some form of security collateral, as in a securities financing transaction. Plus additional description for ICMA.
 * @version ${project.version}
 */
@RosettaDataType(value="AssetPayout", builder=AssetPayout.AssetPayoutBuilderImpl.class, version="${project.version}")
public interface AssetPayout extends PayoutBase, GlobalKey {

	AssetPayoutMeta metaData = new AssetPayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines each asset movement as a buy/sell at different dates, typically 1 near leg and 1 far leg in a securities financing transaction.
	 */
	List<? extends AssetLeg> getAssetLeg();
	/**
	 * Specifies the Purchased Security.  Within SecurityPayout we include a condition which validates that the product must be a Security (see below condition &#39;ProductMustBeSecurity&#39;).
	 */
	Product getSecurityInformation();
	/**
	 * Specifies the Duration Terms of the Security Finance transaction. e.g. Open or Term.
	 */
	Duration getDurationType();
	/**
	 * A contractual minimum amount which the borrower will pay, regardless of the duration of the loan. A mechanism for making sure that a trade generates enough income.
	 */
	Money getMinimumFee();
	/**
	 * Specifies the terms under which dividends received by the borrower are passed through to the lender.
	 */
	DividendTerms getDividendTerms();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	AssetPayout build();
	
	AssetPayout.AssetPayoutBuilder toBuilder();
	
	static AssetPayout.AssetPayoutBuilder builder() {
		return new AssetPayout.AssetPayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AssetPayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AssetPayout> getType() {
		return AssetPayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("assetLeg"), processor, AssetLeg.class, getAssetLeg());
		processRosetta(path.newSubPath("securityInformation"), processor, Product.class, getSecurityInformation());
		processRosetta(path.newSubPath("durationType"), processor, Duration.class, getDurationType());
		processRosetta(path.newSubPath("minimumFee"), processor, Money.class, getMinimumFee());
		processRosetta(path.newSubPath("dividendTerms"), processor, DividendTerms.class, getDividendTerms());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AssetPayoutBuilder extends AssetPayout, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		AssetLeg.AssetLegBuilder getOrCreateAssetLeg(int _index);
		List<? extends AssetLeg.AssetLegBuilder> getAssetLeg();
		Product.ProductBuilder getOrCreateSecurityInformation();
		Product.ProductBuilder getSecurityInformation();
		Duration.DurationBuilder getOrCreateDurationType();
		Duration.DurationBuilder getDurationType();
		Money.MoneyBuilder getOrCreateMinimumFee();
		Money.MoneyBuilder getMinimumFee();
		DividendTerms.DividendTermsBuilder getOrCreateDividendTerms();
		DividendTerms.DividendTermsBuilder getDividendTerms();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		AssetPayout.AssetPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver);
		AssetPayout.AssetPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		AssetPayout.AssetPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		AssetPayout.AssetPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms);
		AssetPayout.AssetPayoutBuilder addAssetLeg(AssetLeg assetLeg0);
		AssetPayout.AssetPayoutBuilder addAssetLeg(AssetLeg assetLeg1, int _idx);
		AssetPayout.AssetPayoutBuilder addAssetLeg(List<? extends AssetLeg> assetLeg2);
		AssetPayout.AssetPayoutBuilder setAssetLeg(List<? extends AssetLeg> assetLeg3);
		AssetPayout.AssetPayoutBuilder setSecurityInformation(Product securityInformation);
		AssetPayout.AssetPayoutBuilder setDurationType(Duration durationType);
		AssetPayout.AssetPayoutBuilder setMinimumFee(Money minimumFee);
		AssetPayout.AssetPayoutBuilder setDividendTerms(DividendTerms dividendTerms);
		AssetPayout.AssetPayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("assetLeg"), processor, AssetLeg.AssetLegBuilder.class, getAssetLeg());
			processRosetta(path.newSubPath("securityInformation"), processor, Product.ProductBuilder.class, getSecurityInformation());
			processRosetta(path.newSubPath("durationType"), processor, Duration.DurationBuilder.class, getDurationType());
			processRosetta(path.newSubPath("minimumFee"), processor, Money.MoneyBuilder.class, getMinimumFee());
			processRosetta(path.newSubPath("dividendTerms"), processor, DividendTerms.DividendTermsBuilder.class, getDividendTerms());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		AssetPayout.AssetPayoutBuilder prune();
	}

	/*********************** Immutable Implementation of AssetPayout  ***********************/
	class AssetPayoutImpl extends PayoutBase.PayoutBaseImpl implements AssetPayout {
		private final List<? extends AssetLeg> assetLeg;
		private final Product securityInformation;
		private final Duration durationType;
		private final Money minimumFee;
		private final DividendTerms dividendTerms;
		private final MetaFields meta;
		
		protected AssetPayoutImpl(AssetPayout.AssetPayoutBuilder builder) {
			super(builder);
			this.assetLeg = ofNullable(builder.getAssetLeg()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.securityInformation = ofNullable(builder.getSecurityInformation()).map(f->f.build()).orElse(null);
			this.durationType = ofNullable(builder.getDurationType()).map(f->f.build()).orElse(null);
			this.minimumFee = ofNullable(builder.getMinimumFee()).map(f->f.build()).orElse(null);
			this.dividendTerms = ofNullable(builder.getDividendTerms()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("assetLeg")
		public List<? extends AssetLeg> getAssetLeg() {
			return assetLeg;
		}
		
		@Override
		@RosettaAttribute("securityInformation")
		public Product getSecurityInformation() {
			return securityInformation;
		}
		
		@Override
		@RosettaAttribute("durationType")
		public Duration getDurationType() {
			return durationType;
		}
		
		@Override
		@RosettaAttribute("minimumFee")
		public Money getMinimumFee() {
			return minimumFee;
		}
		
		@Override
		@RosettaAttribute("dividendTerms")
		public DividendTerms getDividendTerms() {
			return dividendTerms;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public AssetPayout build() {
			return this;
		}
		
		@Override
		public AssetPayout.AssetPayoutBuilder toBuilder() {
			AssetPayout.AssetPayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AssetPayout.AssetPayoutBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getAssetLeg()).ifPresent(builder::setAssetLeg);
			ofNullable(getSecurityInformation()).ifPresent(builder::setSecurityInformation);
			ofNullable(getDurationType()).ifPresent(builder::setDurationType);
			ofNullable(getMinimumFee()).ifPresent(builder::setMinimumFee);
			ofNullable(getDividendTerms()).ifPresent(builder::setDividendTerms);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			AssetPayout _that = getType().cast(o);
		
			if (!ListEquals.listEquals(assetLeg, _that.getAssetLeg())) return false;
			if (!Objects.equals(securityInformation, _that.getSecurityInformation())) return false;
			if (!Objects.equals(durationType, _that.getDurationType())) return false;
			if (!Objects.equals(minimumFee, _that.getMinimumFee())) return false;
			if (!Objects.equals(dividendTerms, _that.getDividendTerms())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (assetLeg != null ? assetLeg.hashCode() : 0);
			_result = 31 * _result + (securityInformation != null ? securityInformation.hashCode() : 0);
			_result = 31 * _result + (durationType != null ? durationType.hashCode() : 0);
			_result = 31 * _result + (minimumFee != null ? minimumFee.hashCode() : 0);
			_result = 31 * _result + (dividendTerms != null ? dividendTerms.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetPayout {" +
				"assetLeg=" + this.assetLeg + ", " +
				"securityInformation=" + this.securityInformation + ", " +
				"durationType=" + this.durationType + ", " +
				"minimumFee=" + this.minimumFee + ", " +
				"dividendTerms=" + this.dividendTerms + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of AssetPayout  ***********************/
	class AssetPayoutBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements AssetPayout.AssetPayoutBuilder, GlobalKeyBuilder {
	
		protected List<AssetLeg.AssetLegBuilder> assetLeg = new ArrayList<>();
		protected Product.ProductBuilder securityInformation;
		protected Duration.DurationBuilder durationType;
		protected Money.MoneyBuilder minimumFee;
		protected DividendTerms.DividendTermsBuilder dividendTerms;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public AssetPayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("assetLeg")
		public List<? extends AssetLeg.AssetLegBuilder> getAssetLeg() {
			return assetLeg;
		}
		
		public AssetLeg.AssetLegBuilder getOrCreateAssetLeg(int _index) {
		
			if (assetLeg==null) {
				this.assetLeg = new ArrayList<>();
			}
			AssetLeg.AssetLegBuilder result;
			return getIndex(assetLeg, _index, () -> {
						AssetLeg.AssetLegBuilder newAssetLeg = AssetLeg.builder();
						return newAssetLeg;
					});
		}
		
		@Override
		@RosettaAttribute("securityInformation")
		public Product.ProductBuilder getSecurityInformation() {
			return securityInformation;
		}
		
		@Override
		public Product.ProductBuilder getOrCreateSecurityInformation() {
			Product.ProductBuilder result;
			if (securityInformation!=null) {
				result = securityInformation;
			}
			else {
				result = securityInformation = Product.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("durationType")
		public Duration.DurationBuilder getDurationType() {
			return durationType;
		}
		
		@Override
		public Duration.DurationBuilder getOrCreateDurationType() {
			Duration.DurationBuilder result;
			if (durationType!=null) {
				result = durationType;
			}
			else {
				result = durationType = Duration.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("minimumFee")
		public Money.MoneyBuilder getMinimumFee() {
			return minimumFee;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateMinimumFee() {
			Money.MoneyBuilder result;
			if (minimumFee!=null) {
				result = minimumFee;
			}
			else {
				result = minimumFee = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dividendTerms")
		public DividendTerms.DividendTermsBuilder getDividendTerms() {
			return dividendTerms;
		}
		
		@Override
		public DividendTerms.DividendTermsBuilder getOrCreateDividendTerms() {
			DividendTerms.DividendTermsBuilder result;
			if (dividendTerms!=null) {
				result = dividendTerms;
			}
			else {
				result = dividendTerms = DividendTerms.builder();
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
		public AssetPayout.AssetPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public AssetPayout.AssetPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public AssetPayout.AssetPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public AssetPayout.AssetPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		public AssetPayout.AssetPayoutBuilder addAssetLeg(AssetLeg assetLeg) {
			if (assetLeg!=null) this.assetLeg.add(assetLeg.toBuilder());
			return this;
		}
		
		@Override
		public AssetPayout.AssetPayoutBuilder addAssetLeg(AssetLeg assetLeg, int _idx) {
			getIndex(this.assetLeg, _idx, () -> assetLeg.toBuilder());
			return this;
		}
		@Override 
		public AssetPayout.AssetPayoutBuilder addAssetLeg(List<? extends AssetLeg> assetLegs) {
			if (assetLegs != null) {
				for (AssetLeg toAdd : assetLegs) {
					this.assetLeg.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("assetLeg")
		public AssetPayout.AssetPayoutBuilder setAssetLeg(List<? extends AssetLeg> assetLegs) {
			if (assetLegs == null)  {
				this.assetLeg = new ArrayList<>();
			}
			else {
				this.assetLeg = assetLegs.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("securityInformation")
		public AssetPayout.AssetPayoutBuilder setSecurityInformation(Product securityInformation) {
			this.securityInformation = securityInformation==null?null:securityInformation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("durationType")
		public AssetPayout.AssetPayoutBuilder setDurationType(Duration durationType) {
			this.durationType = durationType==null?null:durationType.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("minimumFee")
		public AssetPayout.AssetPayoutBuilder setMinimumFee(Money minimumFee) {
			this.minimumFee = minimumFee==null?null:minimumFee.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dividendTerms")
		public AssetPayout.AssetPayoutBuilder setDividendTerms(DividendTerms dividendTerms) {
			this.dividendTerms = dividendTerms==null?null:dividendTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public AssetPayout.AssetPayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public AssetPayout build() {
			return new AssetPayout.AssetPayoutImpl(this);
		}
		
		@Override
		public AssetPayout.AssetPayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetPayout.AssetPayoutBuilder prune() {
			super.prune();
			assetLeg = assetLeg.stream().filter(b->b!=null).<AssetLeg.AssetLegBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (securityInformation!=null && !securityInformation.prune().hasData()) securityInformation = null;
			if (durationType!=null && !durationType.prune().hasData()) durationType = null;
			if (minimumFee!=null && !minimumFee.prune().hasData()) minimumFee = null;
			if (dividendTerms!=null && !dividendTerms.prune().hasData()) dividendTerms = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getAssetLeg()!=null && getAssetLeg().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getSecurityInformation()!=null && getSecurityInformation().hasData()) return true;
			if (getDurationType()!=null && getDurationType().hasData()) return true;
			if (getMinimumFee()!=null && getMinimumFee().hasData()) return true;
			if (getDividendTerms()!=null && getDividendTerms().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetPayout.AssetPayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			AssetPayout.AssetPayoutBuilder o = (AssetPayout.AssetPayoutBuilder) other;
			
			merger.mergeRosetta(getAssetLeg(), o.getAssetLeg(), this::getOrCreateAssetLeg);
			merger.mergeRosetta(getSecurityInformation(), o.getSecurityInformation(), this::setSecurityInformation);
			merger.mergeRosetta(getDurationType(), o.getDurationType(), this::setDurationType);
			merger.mergeRosetta(getMinimumFee(), o.getMinimumFee(), this::setMinimumFee);
			merger.mergeRosetta(getDividendTerms(), o.getDividendTerms(), this::setDividendTerms);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			AssetPayout _that = getType().cast(o);
		
			if (!ListEquals.listEquals(assetLeg, _that.getAssetLeg())) return false;
			if (!Objects.equals(securityInformation, _that.getSecurityInformation())) return false;
			if (!Objects.equals(durationType, _that.getDurationType())) return false;
			if (!Objects.equals(minimumFee, _that.getMinimumFee())) return false;
			if (!Objects.equals(dividendTerms, _that.getDividendTerms())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (assetLeg != null ? assetLeg.hashCode() : 0);
			_result = 31 * _result + (securityInformation != null ? securityInformation.hashCode() : 0);
			_result = 31 * _result + (durationType != null ? durationType.hashCode() : 0);
			_result = 31 * _result + (minimumFee != null ? minimumFee.hashCode() : 0);
			_result = 31 * _result + (dividendTerms != null ? dividendTerms.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetPayoutBuilder {" +
				"assetLeg=" + this.assetLeg + ", " +
				"securityInformation=" + this.securityInformation + ", " +
				"durationType=" + this.durationType + ", " +
				"minimumFee=" + this.minimumFee + ", " +
				"dividendTerms=" + this.dividendTerms + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
