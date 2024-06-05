package cdm.event.common;

import cdm.event.common.CollateralPosition;
import cdm.event.common.MarginCallActionEnum;
import cdm.event.common.MarginCallResponseAction;
import cdm.event.common.MarginCallResponseAction.MarginCallResponseActionBuilder;
import cdm.event.common.MarginCallResponseAction.MarginCallResponseActionBuilderImpl;
import cdm.event.common.MarginCallResponseAction.MarginCallResponseActionImpl;
import cdm.event.common.meta.MarginCallResponseActionMeta;
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
 * Specifies the margin call action details, including collateral to be moved and its direction.
 * @version ${project.version}
 */
@RosettaDataType(value="MarginCallResponseAction", builder=MarginCallResponseAction.MarginCallResponseActionBuilderImpl.class, version="${project.version}")
public interface MarginCallResponseAction extends RosettaModelObject {

	MarginCallResponseActionMeta metaData = new MarginCallResponseActionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the collateral to be moved and its direction.
	 */
	List<? extends CollateralPosition> getCollateralPositionComponent();
	/**
	 * Specifies the margin call action details, specified as either Delivery or Return.
	 */
	MarginCallActionEnum getMarginCallAction();

	/*********************** Build Methods  ***********************/
	MarginCallResponseAction build();
	
	MarginCallResponseAction.MarginCallResponseActionBuilder toBuilder();
	
	static MarginCallResponseAction.MarginCallResponseActionBuilder builder() {
		return new MarginCallResponseAction.MarginCallResponseActionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MarginCallResponseAction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MarginCallResponseAction> getType() {
		return MarginCallResponseAction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("collateralPositionComponent"), processor, CollateralPosition.class, getCollateralPositionComponent());
		processor.processBasic(path.newSubPath("marginCallAction"), MarginCallActionEnum.class, getMarginCallAction(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface MarginCallResponseActionBuilder extends MarginCallResponseAction, RosettaModelObjectBuilder {
		CollateralPosition.CollateralPositionBuilder getOrCreateCollateralPositionComponent(int _index);
		List<? extends CollateralPosition.CollateralPositionBuilder> getCollateralPositionComponent();
		MarginCallResponseAction.MarginCallResponseActionBuilder addCollateralPositionComponent(CollateralPosition collateralPositionComponent0);
		MarginCallResponseAction.MarginCallResponseActionBuilder addCollateralPositionComponent(CollateralPosition collateralPositionComponent1, int _idx);
		MarginCallResponseAction.MarginCallResponseActionBuilder addCollateralPositionComponent(List<? extends CollateralPosition> collateralPositionComponent2);
		MarginCallResponseAction.MarginCallResponseActionBuilder setCollateralPositionComponent(List<? extends CollateralPosition> collateralPositionComponent3);
		MarginCallResponseAction.MarginCallResponseActionBuilder setMarginCallAction(MarginCallActionEnum marginCallAction);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("collateralPositionComponent"), processor, CollateralPosition.CollateralPositionBuilder.class, getCollateralPositionComponent());
			processor.processBasic(path.newSubPath("marginCallAction"), MarginCallActionEnum.class, getMarginCallAction(), this);
		}
		

		MarginCallResponseAction.MarginCallResponseActionBuilder prune();
	}

	/*********************** Immutable Implementation of MarginCallResponseAction  ***********************/
	class MarginCallResponseActionImpl implements MarginCallResponseAction {
		private final List<? extends CollateralPosition> collateralPositionComponent;
		private final MarginCallActionEnum marginCallAction;
		
		protected MarginCallResponseActionImpl(MarginCallResponseAction.MarginCallResponseActionBuilder builder) {
			this.collateralPositionComponent = ofNullable(builder.getCollateralPositionComponent()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.marginCallAction = builder.getMarginCallAction();
		}
		
		@Override
		@RosettaAttribute("collateralPositionComponent")
		public List<? extends CollateralPosition> getCollateralPositionComponent() {
			return collateralPositionComponent;
		}
		
		@Override
		@RosettaAttribute("marginCallAction")
		public MarginCallActionEnum getMarginCallAction() {
			return marginCallAction;
		}
		
		@Override
		public MarginCallResponseAction build() {
			return this;
		}
		
		@Override
		public MarginCallResponseAction.MarginCallResponseActionBuilder toBuilder() {
			MarginCallResponseAction.MarginCallResponseActionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MarginCallResponseAction.MarginCallResponseActionBuilder builder) {
			ofNullable(getCollateralPositionComponent()).ifPresent(builder::setCollateralPositionComponent);
			ofNullable(getMarginCallAction()).ifPresent(builder::setMarginCallAction);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MarginCallResponseAction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(collateralPositionComponent, _that.getCollateralPositionComponent())) return false;
			if (!Objects.equals(marginCallAction, _that.getMarginCallAction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (collateralPositionComponent != null ? collateralPositionComponent.hashCode() : 0);
			_result = 31 * _result + (marginCallAction != null ? marginCallAction.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallResponseAction {" +
				"collateralPositionComponent=" + this.collateralPositionComponent + ", " +
				"marginCallAction=" + this.marginCallAction +
			'}';
		}
	}

	/*********************** Builder Implementation of MarginCallResponseAction  ***********************/
	class MarginCallResponseActionBuilderImpl implements MarginCallResponseAction.MarginCallResponseActionBuilder {
	
		protected List<CollateralPosition.CollateralPositionBuilder> collateralPositionComponent = new ArrayList<>();
		protected MarginCallActionEnum marginCallAction;
	
		public MarginCallResponseActionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("collateralPositionComponent")
		public List<? extends CollateralPosition.CollateralPositionBuilder> getCollateralPositionComponent() {
			return collateralPositionComponent;
		}
		
		public CollateralPosition.CollateralPositionBuilder getOrCreateCollateralPositionComponent(int _index) {
		
			if (collateralPositionComponent==null) {
				this.collateralPositionComponent = new ArrayList<>();
			}
			CollateralPosition.CollateralPositionBuilder result;
			return getIndex(collateralPositionComponent, _index, () -> {
						CollateralPosition.CollateralPositionBuilder newCollateralPositionComponent = CollateralPosition.builder();
						return newCollateralPositionComponent;
					});
		}
		
		@Override
		@RosettaAttribute("marginCallAction")
		public MarginCallActionEnum getMarginCallAction() {
			return marginCallAction;
		}
		
	
		@Override
		public MarginCallResponseAction.MarginCallResponseActionBuilder addCollateralPositionComponent(CollateralPosition collateralPositionComponent) {
			if (collateralPositionComponent!=null) this.collateralPositionComponent.add(collateralPositionComponent.toBuilder());
			return this;
		}
		
		@Override
		public MarginCallResponseAction.MarginCallResponseActionBuilder addCollateralPositionComponent(CollateralPosition collateralPositionComponent, int _idx) {
			getIndex(this.collateralPositionComponent, _idx, () -> collateralPositionComponent.toBuilder());
			return this;
		}
		@Override 
		public MarginCallResponseAction.MarginCallResponseActionBuilder addCollateralPositionComponent(List<? extends CollateralPosition> collateralPositionComponents) {
			if (collateralPositionComponents != null) {
				for (CollateralPosition toAdd : collateralPositionComponents) {
					this.collateralPositionComponent.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("collateralPositionComponent")
		public MarginCallResponseAction.MarginCallResponseActionBuilder setCollateralPositionComponent(List<? extends CollateralPosition> collateralPositionComponents) {
			if (collateralPositionComponents == null)  {
				this.collateralPositionComponent = new ArrayList<>();
			}
			else {
				this.collateralPositionComponent = collateralPositionComponents.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("marginCallAction")
		public MarginCallResponseAction.MarginCallResponseActionBuilder setMarginCallAction(MarginCallActionEnum marginCallAction) {
			this.marginCallAction = marginCallAction==null?null:marginCallAction;
			return this;
		}
		
		@Override
		public MarginCallResponseAction build() {
			return new MarginCallResponseAction.MarginCallResponseActionImpl(this);
		}
		
		@Override
		public MarginCallResponseAction.MarginCallResponseActionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallResponseAction.MarginCallResponseActionBuilder prune() {
			collateralPositionComponent = collateralPositionComponent.stream().filter(b->b!=null).<CollateralPosition.CollateralPositionBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCollateralPositionComponent()!=null && getCollateralPositionComponent().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getMarginCallAction()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallResponseAction.MarginCallResponseActionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MarginCallResponseAction.MarginCallResponseActionBuilder o = (MarginCallResponseAction.MarginCallResponseActionBuilder) other;
			
			merger.mergeRosetta(getCollateralPositionComponent(), o.getCollateralPositionComponent(), this::getOrCreateCollateralPositionComponent);
			
			merger.mergeBasic(getMarginCallAction(), o.getMarginCallAction(), this::setMarginCallAction);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MarginCallResponseAction _that = getType().cast(o);
		
			if (!ListEquals.listEquals(collateralPositionComponent, _that.getCollateralPositionComponent())) return false;
			if (!Objects.equals(marginCallAction, _that.getMarginCallAction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (collateralPositionComponent != null ? collateralPositionComponent.hashCode() : 0);
			_result = 31 * _result + (marginCallAction != null ? marginCallAction.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallResponseActionBuilder {" +
				"collateralPositionComponent=" + this.collateralPositionComponent + ", " +
				"marginCallAction=" + this.marginCallAction +
			'}';
		}
	}
}
