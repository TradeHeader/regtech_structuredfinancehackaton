package cdm.product.asset;

import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.AssetDeliveryProfile.AssetDeliveryProfileBuilder;
import cdm.product.asset.AssetDeliveryProfile.AssetDeliveryProfileBuilderImpl;
import cdm.product.asset.AssetDeliveryProfile.AssetDeliveryProfileImpl;
import cdm.product.asset.AssetDeliveryProfileBlock;
import cdm.product.asset.BankHolidayTreatmentEnum;
import cdm.product.asset.LoadTypeEnum;
import cdm.product.asset.meta.AssetDeliveryProfileMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Defines the delivery profile of the asset, including the load type and the delivery intervals.
 * @version ${project.version}
 */
@RosettaDataType(value="AssetDeliveryProfile", builder=AssetDeliveryProfile.AssetDeliveryProfileBuilderImpl.class, version="${project.version}")
public interface AssetDeliveryProfile extends RosettaModelObject {

	AssetDeliveryProfileMeta metaData = new AssetDeliveryProfileMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identification of the delivery profile.
	 */
	LoadTypeEnum getLoadType();
	/**
	 * Defines a delivery profile block, including start and end time, days of the week, duration, delivery capacity and price time interval quantity.
	 */
	List<? extends AssetDeliveryProfileBlock> getBlock();
	/**
	 * Specifies whether the dates defined include holidays or not.
	 */
	BankHolidayTreatmentEnum getBankHolidaysTreatment();

	/*********************** Build Methods  ***********************/
	AssetDeliveryProfile build();
	
	AssetDeliveryProfile.AssetDeliveryProfileBuilder toBuilder();
	
	static AssetDeliveryProfile.AssetDeliveryProfileBuilder builder() {
		return new AssetDeliveryProfile.AssetDeliveryProfileBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AssetDeliveryProfile> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AssetDeliveryProfile> getType() {
		return AssetDeliveryProfile.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("loadType"), LoadTypeEnum.class, getLoadType(), this);
		processRosetta(path.newSubPath("block"), processor, AssetDeliveryProfileBlock.class, getBlock());
		processor.processBasic(path.newSubPath("bankHolidaysTreatment"), BankHolidayTreatmentEnum.class, getBankHolidaysTreatment(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AssetDeliveryProfileBuilder extends AssetDeliveryProfile, RosettaModelObjectBuilder {
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder getOrCreateBlock(int _index);
		List<? extends AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder> getBlock();
		AssetDeliveryProfile.AssetDeliveryProfileBuilder setLoadType(LoadTypeEnum loadType);
		AssetDeliveryProfile.AssetDeliveryProfileBuilder addBlock(AssetDeliveryProfileBlock block0);
		AssetDeliveryProfile.AssetDeliveryProfileBuilder addBlock(AssetDeliveryProfileBlock block1, int _idx);
		AssetDeliveryProfile.AssetDeliveryProfileBuilder addBlock(List<? extends AssetDeliveryProfileBlock> block2);
		AssetDeliveryProfile.AssetDeliveryProfileBuilder setBlock(List<? extends AssetDeliveryProfileBlock> block3);
		AssetDeliveryProfile.AssetDeliveryProfileBuilder setBankHolidaysTreatment(BankHolidayTreatmentEnum bankHolidaysTreatment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("loadType"), LoadTypeEnum.class, getLoadType(), this);
			processRosetta(path.newSubPath("block"), processor, AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder.class, getBlock());
			processor.processBasic(path.newSubPath("bankHolidaysTreatment"), BankHolidayTreatmentEnum.class, getBankHolidaysTreatment(), this);
		}
		

		AssetDeliveryProfile.AssetDeliveryProfileBuilder prune();
	}

	/*********************** Immutable Implementation of AssetDeliveryProfile  ***********************/
	class AssetDeliveryProfileImpl implements AssetDeliveryProfile {
		private final LoadTypeEnum loadType;
		private final List<? extends AssetDeliveryProfileBlock> block;
		private final BankHolidayTreatmentEnum bankHolidaysTreatment;
		
		protected AssetDeliveryProfileImpl(AssetDeliveryProfile.AssetDeliveryProfileBuilder builder) {
			this.loadType = builder.getLoadType();
			this.block = ofNullable(builder.getBlock()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.bankHolidaysTreatment = builder.getBankHolidaysTreatment();
		}
		
		@Override
		@RosettaAttribute("loadType")
		public LoadTypeEnum getLoadType() {
			return loadType;
		}
		
		@Override
		@RosettaAttribute("block")
		public List<? extends AssetDeliveryProfileBlock> getBlock() {
			return block;
		}
		
		@Override
		@RosettaAttribute("bankHolidaysTreatment")
		public BankHolidayTreatmentEnum getBankHolidaysTreatment() {
			return bankHolidaysTreatment;
		}
		
		@Override
		public AssetDeliveryProfile build() {
			return this;
		}
		
		@Override
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder toBuilder() {
			AssetDeliveryProfile.AssetDeliveryProfileBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AssetDeliveryProfile.AssetDeliveryProfileBuilder builder) {
			ofNullable(getLoadType()).ifPresent(builder::setLoadType);
			ofNullable(getBlock()).ifPresent(builder::setBlock);
			ofNullable(getBankHolidaysTreatment()).ifPresent(builder::setBankHolidaysTreatment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetDeliveryProfile _that = getType().cast(o);
		
			if (!Objects.equals(loadType, _that.getLoadType())) return false;
			if (!ListEquals.listEquals(block, _that.getBlock())) return false;
			if (!Objects.equals(bankHolidaysTreatment, _that.getBankHolidaysTreatment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (loadType != null ? loadType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (block != null ? block.hashCode() : 0);
			_result = 31 * _result + (bankHolidaysTreatment != null ? bankHolidaysTreatment.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetDeliveryProfile {" +
				"loadType=" + this.loadType + ", " +
				"block=" + this.block + ", " +
				"bankHolidaysTreatment=" + this.bankHolidaysTreatment +
			'}';
		}
	}

	/*********************** Builder Implementation of AssetDeliveryProfile  ***********************/
	class AssetDeliveryProfileBuilderImpl implements AssetDeliveryProfile.AssetDeliveryProfileBuilder {
	
		protected LoadTypeEnum loadType;
		protected List<AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder> block = new ArrayList<>();
		protected BankHolidayTreatmentEnum bankHolidaysTreatment;
	
		public AssetDeliveryProfileBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("loadType")
		public LoadTypeEnum getLoadType() {
			return loadType;
		}
		
		@Override
		@RosettaAttribute("block")
		public List<? extends AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder> getBlock() {
			return block;
		}
		
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder getOrCreateBlock(int _index) {
		
			if (block==null) {
				this.block = new ArrayList<>();
			}
			AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder result;
			return getIndex(block, _index, () -> {
						AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder newBlock = AssetDeliveryProfileBlock.builder();
						return newBlock;
					});
		}
		
		@Override
		@RosettaAttribute("bankHolidaysTreatment")
		public BankHolidayTreatmentEnum getBankHolidaysTreatment() {
			return bankHolidaysTreatment;
		}
		
	
		@Override
		@RosettaAttribute("loadType")
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder setLoadType(LoadTypeEnum loadType) {
			this.loadType = loadType==null?null:loadType;
			return this;
		}
		@Override
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder addBlock(AssetDeliveryProfileBlock block) {
			if (block!=null) this.block.add(block.toBuilder());
			return this;
		}
		
		@Override
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder addBlock(AssetDeliveryProfileBlock block, int _idx) {
			getIndex(this.block, _idx, () -> block.toBuilder());
			return this;
		}
		@Override 
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder addBlock(List<? extends AssetDeliveryProfileBlock> blocks) {
			if (blocks != null) {
				for (AssetDeliveryProfileBlock toAdd : blocks) {
					this.block.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("block")
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder setBlock(List<? extends AssetDeliveryProfileBlock> blocks) {
			if (blocks == null)  {
				this.block = new ArrayList<>();
			}
			else {
				this.block = blocks.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("bankHolidaysTreatment")
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder setBankHolidaysTreatment(BankHolidayTreatmentEnum bankHolidaysTreatment) {
			this.bankHolidaysTreatment = bankHolidaysTreatment==null?null:bankHolidaysTreatment;
			return this;
		}
		
		@Override
		public AssetDeliveryProfile build() {
			return new AssetDeliveryProfile.AssetDeliveryProfileImpl(this);
		}
		
		@Override
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder prune() {
			block = block.stream().filter(b->b!=null).<AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLoadType()!=null) return true;
			if (getBlock()!=null && getBlock().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getBankHolidaysTreatment()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetDeliveryProfile.AssetDeliveryProfileBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AssetDeliveryProfile.AssetDeliveryProfileBuilder o = (AssetDeliveryProfile.AssetDeliveryProfileBuilder) other;
			
			merger.mergeRosetta(getBlock(), o.getBlock(), this::getOrCreateBlock);
			
			merger.mergeBasic(getLoadType(), o.getLoadType(), this::setLoadType);
			merger.mergeBasic(getBankHolidaysTreatment(), o.getBankHolidaysTreatment(), this::setBankHolidaysTreatment);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetDeliveryProfile _that = getType().cast(o);
		
			if (!Objects.equals(loadType, _that.getLoadType())) return false;
			if (!ListEquals.listEquals(block, _that.getBlock())) return false;
			if (!Objects.equals(bankHolidaysTreatment, _that.getBankHolidaysTreatment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (loadType != null ? loadType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (block != null ? block.hashCode() : 0);
			_result = 31 * _result + (bankHolidaysTreatment != null ? bankHolidaysTreatment.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetDeliveryProfileBuilder {" +
				"loadType=" + this.loadType + ", " +
				"block=" + this.block + ", " +
				"bankHolidaysTreatment=" + this.bankHolidaysTreatment +
			'}';
		}
	}
}
