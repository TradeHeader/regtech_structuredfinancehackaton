package cdm.base.staticdata.identifier;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.base.staticdata.identifier.IdentifiedList.IdentifiedListBuilder;
import cdm.base.staticdata.identifier.IdentifiedList.IdentifiedListBuilderImpl;
import cdm.base.staticdata.identifier.IdentifiedList.IdentifiedListImpl;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.meta.IdentifiedListMeta;
import cdm.observable.asset.Price;
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
 * Attaches an identifier to a collection of objects, when those objects themselves can each be represented by an identifier. One use case is the representation of package transactions, where each component is a separate trade with its own identifier, and those trades are linked together as a package with its own identifier. The data type has been named generically rather than referring to &#39;packages&#39; as it may have a number of other uses.
 * @version ${project.version}
 */
@RosettaDataType(value="IdentifiedList", builder=IdentifiedList.IdentifiedListBuilderImpl.class, version="${project.version}")
public interface IdentifiedList extends RosettaModelObject, GlobalKey {

	IdentifiedListMeta metaData = new IdentifiedListMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The identifier for the list. In the case of a package transaction, this would be the package identifier. This attribute is mandatory to allow the list itself to be identified.
	 */
	Identifier getListId();
	/**
	 * Identifiers for each component of the list. Since the data type is used to link multiple identified objects together, at least 2 components are required in the list. Creating an identified list with only 1 identified component has been deemed unnecessary, because it would just create a redundant identifier.
	 */
	List<? extends Identifier> getComponentId();
	/**
	 * The price of the package.
	 */
	Price getPrice();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	IdentifiedList build();
	
	IdentifiedList.IdentifiedListBuilder toBuilder();
	
	static IdentifiedList.IdentifiedListBuilder builder() {
		return new IdentifiedList.IdentifiedListBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends IdentifiedList> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends IdentifiedList> getType() {
		return IdentifiedList.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("listId"), processor, Identifier.class, getListId());
		processRosetta(path.newSubPath("componentId"), processor, Identifier.class, getComponentId());
		processRosetta(path.newSubPath("price"), processor, Price.class, getPrice());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface IdentifiedListBuilder extends IdentifiedList, RosettaModelObjectBuilder {
		Identifier.IdentifierBuilder getOrCreateListId();
		Identifier.IdentifierBuilder getListId();
		Identifier.IdentifierBuilder getOrCreateComponentId(int _index);
		List<? extends Identifier.IdentifierBuilder> getComponentId();
		Price.PriceBuilder getOrCreatePrice();
		Price.PriceBuilder getPrice();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		IdentifiedList.IdentifiedListBuilder setListId(Identifier listId);
		IdentifiedList.IdentifiedListBuilder addComponentId(Identifier componentId0);
		IdentifiedList.IdentifiedListBuilder addComponentId(Identifier componentId1, int _idx);
		IdentifiedList.IdentifiedListBuilder addComponentId(List<? extends Identifier> componentId2);
		IdentifiedList.IdentifiedListBuilder setComponentId(List<? extends Identifier> componentId3);
		IdentifiedList.IdentifiedListBuilder setPrice(Price price);
		IdentifiedList.IdentifiedListBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("listId"), processor, Identifier.IdentifierBuilder.class, getListId());
			processRosetta(path.newSubPath("componentId"), processor, Identifier.IdentifierBuilder.class, getComponentId());
			processRosetta(path.newSubPath("price"), processor, Price.PriceBuilder.class, getPrice());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		IdentifiedList.IdentifiedListBuilder prune();
	}

	/*********************** Immutable Implementation of IdentifiedList  ***********************/
	class IdentifiedListImpl implements IdentifiedList {
		private final Identifier listId;
		private final List<? extends Identifier> componentId;
		private final Price price;
		private final MetaFields meta;
		
		protected IdentifiedListImpl(IdentifiedList.IdentifiedListBuilder builder) {
			this.listId = ofNullable(builder.getListId()).map(f->f.build()).orElse(null);
			this.componentId = ofNullable(builder.getComponentId()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.price = ofNullable(builder.getPrice()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("listId")
		public Identifier getListId() {
			return listId;
		}
		
		@Override
		@RosettaAttribute("componentId")
		public List<? extends Identifier> getComponentId() {
			return componentId;
		}
		
		@Override
		@RosettaAttribute("price")
		public Price getPrice() {
			return price;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public IdentifiedList build() {
			return this;
		}
		
		@Override
		public IdentifiedList.IdentifiedListBuilder toBuilder() {
			IdentifiedList.IdentifiedListBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(IdentifiedList.IdentifiedListBuilder builder) {
			ofNullable(getListId()).ifPresent(builder::setListId);
			ofNullable(getComponentId()).ifPresent(builder::setComponentId);
			ofNullable(getPrice()).ifPresent(builder::setPrice);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			IdentifiedList _that = getType().cast(o);
		
			if (!Objects.equals(listId, _that.getListId())) return false;
			if (!ListEquals.listEquals(componentId, _that.getComponentId())) return false;
			if (!Objects.equals(price, _that.getPrice())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (listId != null ? listId.hashCode() : 0);
			_result = 31 * _result + (componentId != null ? componentId.hashCode() : 0);
			_result = 31 * _result + (price != null ? price.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IdentifiedList {" +
				"listId=" + this.listId + ", " +
				"componentId=" + this.componentId + ", " +
				"price=" + this.price + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of IdentifiedList  ***********************/
	class IdentifiedListBuilderImpl implements IdentifiedList.IdentifiedListBuilder, GlobalKeyBuilder {
	
		protected Identifier.IdentifierBuilder listId;
		protected List<Identifier.IdentifierBuilder> componentId = new ArrayList<>();
		protected Price.PriceBuilder price;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public IdentifiedListBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("listId")
		public Identifier.IdentifierBuilder getListId() {
			return listId;
		}
		
		@Override
		public Identifier.IdentifierBuilder getOrCreateListId() {
			Identifier.IdentifierBuilder result;
			if (listId!=null) {
				result = listId;
			}
			else {
				result = listId = Identifier.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("componentId")
		public List<? extends Identifier.IdentifierBuilder> getComponentId() {
			return componentId;
		}
		
		public Identifier.IdentifierBuilder getOrCreateComponentId(int _index) {
		
			if (componentId==null) {
				this.componentId = new ArrayList<>();
			}
			Identifier.IdentifierBuilder result;
			return getIndex(componentId, _index, () -> {
						Identifier.IdentifierBuilder newComponentId = Identifier.builder();
						return newComponentId;
					});
		}
		
		@Override
		@RosettaAttribute("price")
		public Price.PriceBuilder getPrice() {
			return price;
		}
		
		@Override
		public Price.PriceBuilder getOrCreatePrice() {
			Price.PriceBuilder result;
			if (price!=null) {
				result = price;
			}
			else {
				result = price = Price.builder();
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
		@RosettaAttribute("listId")
		public IdentifiedList.IdentifiedListBuilder setListId(Identifier listId) {
			this.listId = listId==null?null:listId.toBuilder();
			return this;
		}
		@Override
		public IdentifiedList.IdentifiedListBuilder addComponentId(Identifier componentId) {
			if (componentId!=null) this.componentId.add(componentId.toBuilder());
			return this;
		}
		
		@Override
		public IdentifiedList.IdentifiedListBuilder addComponentId(Identifier componentId, int _idx) {
			getIndex(this.componentId, _idx, () -> componentId.toBuilder());
			return this;
		}
		@Override 
		public IdentifiedList.IdentifiedListBuilder addComponentId(List<? extends Identifier> componentIds) {
			if (componentIds != null) {
				for (Identifier toAdd : componentIds) {
					this.componentId.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("componentId")
		public IdentifiedList.IdentifiedListBuilder setComponentId(List<? extends Identifier> componentIds) {
			if (componentIds == null)  {
				this.componentId = new ArrayList<>();
			}
			else {
				this.componentId = componentIds.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("price")
		public IdentifiedList.IdentifiedListBuilder setPrice(Price price) {
			this.price = price==null?null:price.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public IdentifiedList.IdentifiedListBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public IdentifiedList build() {
			return new IdentifiedList.IdentifiedListImpl(this);
		}
		
		@Override
		public IdentifiedList.IdentifiedListBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public IdentifiedList.IdentifiedListBuilder prune() {
			if (listId!=null && !listId.prune().hasData()) listId = null;
			componentId = componentId.stream().filter(b->b!=null).<Identifier.IdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (price!=null && !price.prune().hasData()) price = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getListId()!=null && getListId().hasData()) return true;
			if (getComponentId()!=null && getComponentId().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPrice()!=null && getPrice().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public IdentifiedList.IdentifiedListBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			IdentifiedList.IdentifiedListBuilder o = (IdentifiedList.IdentifiedListBuilder) other;
			
			merger.mergeRosetta(getListId(), o.getListId(), this::setListId);
			merger.mergeRosetta(getComponentId(), o.getComponentId(), this::getOrCreateComponentId);
			merger.mergeRosetta(getPrice(), o.getPrice(), this::setPrice);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			IdentifiedList _that = getType().cast(o);
		
			if (!Objects.equals(listId, _that.getListId())) return false;
			if (!ListEquals.listEquals(componentId, _that.getComponentId())) return false;
			if (!Objects.equals(price, _that.getPrice())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (listId != null ? listId.hashCode() : 0);
			_result = 31 * _result + (componentId != null ? componentId.hashCode() : 0);
			_result = 31 * _result + (price != null ? price.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "IdentifiedListBuilder {" +
				"listId=" + this.listId + ", " +
				"componentId=" + this.componentId + ", " +
				"price=" + this.price + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
