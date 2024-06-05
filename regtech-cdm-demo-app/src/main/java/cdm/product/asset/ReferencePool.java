package cdm.product.asset;

import cdm.product.asset.ReferencePool;
import cdm.product.asset.ReferencePool.ReferencePoolBuilder;
import cdm.product.asset.ReferencePool.ReferencePoolBuilderImpl;
import cdm.product.asset.ReferencePool.ReferencePoolImpl;
import cdm.product.asset.ReferencePoolItem;
import cdm.product.asset.meta.ReferencePoolMeta;
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
 * This type contains all the reference pool items to define the reference entity and reference obligation(s) in the basket.
 * @version ${project.version}
 */
@RosettaDataType(value="ReferencePool", builder=ReferencePool.ReferencePoolBuilderImpl.class, version="${project.version}")
public interface ReferencePool extends RosettaModelObject {

	ReferencePoolMeta metaData = new ReferencePoolMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * This type contains all the constituent weight and reference information.
	 */
	List<? extends ReferencePoolItem> getReferencePoolItem();

	/*********************** Build Methods  ***********************/
	ReferencePool build();
	
	ReferencePool.ReferencePoolBuilder toBuilder();
	
	static ReferencePool.ReferencePoolBuilder builder() {
		return new ReferencePool.ReferencePoolBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferencePool> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferencePool> getType() {
		return ReferencePool.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("referencePoolItem"), processor, ReferencePoolItem.class, getReferencePoolItem());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferencePoolBuilder extends ReferencePool, RosettaModelObjectBuilder {
		ReferencePoolItem.ReferencePoolItemBuilder getOrCreateReferencePoolItem(int _index);
		List<? extends ReferencePoolItem.ReferencePoolItemBuilder> getReferencePoolItem();
		ReferencePool.ReferencePoolBuilder addReferencePoolItem(ReferencePoolItem referencePoolItem0);
		ReferencePool.ReferencePoolBuilder addReferencePoolItem(ReferencePoolItem referencePoolItem1, int _idx);
		ReferencePool.ReferencePoolBuilder addReferencePoolItem(List<? extends ReferencePoolItem> referencePoolItem2);
		ReferencePool.ReferencePoolBuilder setReferencePoolItem(List<? extends ReferencePoolItem> referencePoolItem3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("referencePoolItem"), processor, ReferencePoolItem.ReferencePoolItemBuilder.class, getReferencePoolItem());
		}
		

		ReferencePool.ReferencePoolBuilder prune();
	}

	/*********************** Immutable Implementation of ReferencePool  ***********************/
	class ReferencePoolImpl implements ReferencePool {
		private final List<? extends ReferencePoolItem> referencePoolItem;
		
		protected ReferencePoolImpl(ReferencePool.ReferencePoolBuilder builder) {
			this.referencePoolItem = ofNullable(builder.getReferencePoolItem()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("referencePoolItem")
		public List<? extends ReferencePoolItem> getReferencePoolItem() {
			return referencePoolItem;
		}
		
		@Override
		public ReferencePool build() {
			return this;
		}
		
		@Override
		public ReferencePool.ReferencePoolBuilder toBuilder() {
			ReferencePool.ReferencePoolBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferencePool.ReferencePoolBuilder builder) {
			ofNullable(getReferencePoolItem()).ifPresent(builder::setReferencePoolItem);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferencePool _that = getType().cast(o);
		
			if (!ListEquals.listEquals(referencePoolItem, _that.getReferencePoolItem())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referencePoolItem != null ? referencePoolItem.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferencePool {" +
				"referencePoolItem=" + this.referencePoolItem +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferencePool  ***********************/
	class ReferencePoolBuilderImpl implements ReferencePool.ReferencePoolBuilder {
	
		protected List<ReferencePoolItem.ReferencePoolItemBuilder> referencePoolItem = new ArrayList<>();
	
		public ReferencePoolBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("referencePoolItem")
		public List<? extends ReferencePoolItem.ReferencePoolItemBuilder> getReferencePoolItem() {
			return referencePoolItem;
		}
		
		public ReferencePoolItem.ReferencePoolItemBuilder getOrCreateReferencePoolItem(int _index) {
		
			if (referencePoolItem==null) {
				this.referencePoolItem = new ArrayList<>();
			}
			ReferencePoolItem.ReferencePoolItemBuilder result;
			return getIndex(referencePoolItem, _index, () -> {
						ReferencePoolItem.ReferencePoolItemBuilder newReferencePoolItem = ReferencePoolItem.builder();
						return newReferencePoolItem;
					});
		}
		
	
		@Override
		public ReferencePool.ReferencePoolBuilder addReferencePoolItem(ReferencePoolItem referencePoolItem) {
			if (referencePoolItem!=null) this.referencePoolItem.add(referencePoolItem.toBuilder());
			return this;
		}
		
		@Override
		public ReferencePool.ReferencePoolBuilder addReferencePoolItem(ReferencePoolItem referencePoolItem, int _idx) {
			getIndex(this.referencePoolItem, _idx, () -> referencePoolItem.toBuilder());
			return this;
		}
		@Override 
		public ReferencePool.ReferencePoolBuilder addReferencePoolItem(List<? extends ReferencePoolItem> referencePoolItems) {
			if (referencePoolItems != null) {
				for (ReferencePoolItem toAdd : referencePoolItems) {
					this.referencePoolItem.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("referencePoolItem")
		public ReferencePool.ReferencePoolBuilder setReferencePoolItem(List<? extends ReferencePoolItem> referencePoolItems) {
			if (referencePoolItems == null)  {
				this.referencePoolItem = new ArrayList<>();
			}
			else {
				this.referencePoolItem = referencePoolItems.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public ReferencePool build() {
			return new ReferencePool.ReferencePoolImpl(this);
		}
		
		@Override
		public ReferencePool.ReferencePoolBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferencePool.ReferencePoolBuilder prune() {
			referencePoolItem = referencePoolItem.stream().filter(b->b!=null).<ReferencePoolItem.ReferencePoolItemBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getReferencePoolItem()!=null && getReferencePoolItem().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferencePool.ReferencePoolBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferencePool.ReferencePoolBuilder o = (ReferencePool.ReferencePoolBuilder) other;
			
			merger.mergeRosetta(getReferencePoolItem(), o.getReferencePoolItem(), this::getOrCreateReferencePoolItem);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferencePool _that = getType().cast(o);
		
			if (!ListEquals.listEquals(referencePoolItem, _that.getReferencePoolItem())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referencePoolItem != null ? referencePoolItem.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferencePoolBuilder {" +
				"referencePoolItem=" + this.referencePoolItem +
			'}';
		}
	}
}
