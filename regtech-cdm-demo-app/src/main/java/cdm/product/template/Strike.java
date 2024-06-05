package cdm.product.template;

import cdm.base.staticdata.party.PayerReceiverEnum;
import cdm.product.template.Strike;
import cdm.product.template.Strike.StrikeBuilder;
import cdm.product.template.Strike.StrikeBuilderImpl;
import cdm.product.template.Strike.StrikeImpl;
import cdm.product.template.meta.StrikeMeta;
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
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class describing a single cap or floor rate.
 * @version ${project.version}
 */
@RosettaDataType(value="Strike", builder=Strike.StrikeBuilderImpl.class, version="${project.version}")
public interface Strike extends RosettaModelObject, GlobalKey {

	StrikeMeta metaData = new StrikeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The rate for a cap or floor.
	 */
	BigDecimal getStrikeRate();
	/**
	 * The buyer of the option.
	 */
	PayerReceiverEnum getBuyer();
	/**
	 * The party that has sold.
	 */
	PayerReceiverEnum getSeller();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Strike build();
	
	Strike.StrikeBuilder toBuilder();
	
	static Strike.StrikeBuilder builder() {
		return new Strike.StrikeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Strike> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Strike> getType() {
		return Strike.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("strikeRate"), BigDecimal.class, getStrikeRate(), this);
		processor.processBasic(path.newSubPath("buyer"), PayerReceiverEnum.class, getBuyer(), this);
		processor.processBasic(path.newSubPath("seller"), PayerReceiverEnum.class, getSeller(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface StrikeBuilder extends Strike, RosettaModelObjectBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Strike.StrikeBuilder setStrikeRate(BigDecimal strikeRate);
		Strike.StrikeBuilder setBuyer(PayerReceiverEnum buyer);
		Strike.StrikeBuilder setSeller(PayerReceiverEnum seller);
		Strike.StrikeBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("strikeRate"), BigDecimal.class, getStrikeRate(), this);
			processor.processBasic(path.newSubPath("buyer"), PayerReceiverEnum.class, getBuyer(), this);
			processor.processBasic(path.newSubPath("seller"), PayerReceiverEnum.class, getSeller(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Strike.StrikeBuilder prune();
	}

	/*********************** Immutable Implementation of Strike  ***********************/
	class StrikeImpl implements Strike {
		private final BigDecimal strikeRate;
		private final PayerReceiverEnum buyer;
		private final PayerReceiverEnum seller;
		private final MetaFields meta;
		
		protected StrikeImpl(Strike.StrikeBuilder builder) {
			this.strikeRate = builder.getStrikeRate();
			this.buyer = builder.getBuyer();
			this.seller = builder.getSeller();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("strikeRate")
		public BigDecimal getStrikeRate() {
			return strikeRate;
		}
		
		@Override
		@RosettaAttribute("buyer")
		public PayerReceiverEnum getBuyer() {
			return buyer;
		}
		
		@Override
		@RosettaAttribute("seller")
		public PayerReceiverEnum getSeller() {
			return seller;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Strike build() {
			return this;
		}
		
		@Override
		public Strike.StrikeBuilder toBuilder() {
			Strike.StrikeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Strike.StrikeBuilder builder) {
			ofNullable(getStrikeRate()).ifPresent(builder::setStrikeRate);
			ofNullable(getBuyer()).ifPresent(builder::setBuyer);
			ofNullable(getSeller()).ifPresent(builder::setSeller);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Strike _that = getType().cast(o);
		
			if (!Objects.equals(strikeRate, _that.getStrikeRate())) return false;
			if (!Objects.equals(buyer, _that.getBuyer())) return false;
			if (!Objects.equals(seller, _that.getSeller())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (strikeRate != null ? strikeRate.hashCode() : 0);
			_result = 31 * _result + (buyer != null ? buyer.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (seller != null ? seller.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Strike {" +
				"strikeRate=" + this.strikeRate + ", " +
				"buyer=" + this.buyer + ", " +
				"seller=" + this.seller + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Strike  ***********************/
	class StrikeBuilderImpl implements Strike.StrikeBuilder, GlobalKeyBuilder {
	
		protected BigDecimal strikeRate;
		protected PayerReceiverEnum buyer;
		protected PayerReceiverEnum seller;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public StrikeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("strikeRate")
		public BigDecimal getStrikeRate() {
			return strikeRate;
		}
		
		@Override
		@RosettaAttribute("buyer")
		public PayerReceiverEnum getBuyer() {
			return buyer;
		}
		
		@Override
		@RosettaAttribute("seller")
		public PayerReceiverEnum getSeller() {
			return seller;
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
		@RosettaAttribute("strikeRate")
		public Strike.StrikeBuilder setStrikeRate(BigDecimal strikeRate) {
			this.strikeRate = strikeRate==null?null:strikeRate;
			return this;
		}
		@Override
		@RosettaAttribute("buyer")
		public Strike.StrikeBuilder setBuyer(PayerReceiverEnum buyer) {
			this.buyer = buyer==null?null:buyer;
			return this;
		}
		@Override
		@RosettaAttribute("seller")
		public Strike.StrikeBuilder setSeller(PayerReceiverEnum seller) {
			this.seller = seller==null?null:seller;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Strike.StrikeBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Strike build() {
			return new Strike.StrikeImpl(this);
		}
		
		@Override
		public Strike.StrikeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Strike.StrikeBuilder prune() {
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStrikeRate()!=null) return true;
			if (getBuyer()!=null) return true;
			if (getSeller()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Strike.StrikeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Strike.StrikeBuilder o = (Strike.StrikeBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getStrikeRate(), o.getStrikeRate(), this::setStrikeRate);
			merger.mergeBasic(getBuyer(), o.getBuyer(), this::setBuyer);
			merger.mergeBasic(getSeller(), o.getSeller(), this::setSeller);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Strike _that = getType().cast(o);
		
			if (!Objects.equals(strikeRate, _that.getStrikeRate())) return false;
			if (!Objects.equals(buyer, _that.getBuyer())) return false;
			if (!Objects.equals(seller, _that.getSeller())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (strikeRate != null ? strikeRate.hashCode() : 0);
			_result = 31 * _result + (buyer != null ? buyer.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (seller != null ? seller.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StrikeBuilder {" +
				"strikeRate=" + this.strikeRate + ", " +
				"buyer=" + this.buyer + ", " +
				"seller=" + this.seller + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
