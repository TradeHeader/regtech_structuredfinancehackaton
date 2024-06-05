package cdm.product.common.settlement;

import cdm.observable.asset.Money;
import cdm.product.common.settlement.ShapingProvision;
import cdm.product.common.settlement.ShapingProvision.ShapingProvisionBuilder;
import cdm.product.common.settlement.ShapingProvision.ShapingProvisionBuilderImpl;
import cdm.product.common.settlement.ShapingProvision.ShapingProvisionImpl;
import cdm.product.common.settlement.meta.ShapingProvisionMeta;
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
 * Defines the applicable settlement limits that may require a settlement to be &#39;shaped&#39;, i.e. broken-down into smaller amounts.
 * @version ${project.version}
 */
@RosettaDataType(value="ShapingProvision", builder=ShapingProvision.ShapingProvisionBuilderImpl.class, version="${project.version}")
public interface ShapingProvision extends RosettaModelObject {

	ShapingProvisionMeta metaData = new ShapingProvisionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines applicable settlement limits in each currency.
	 */
	List<? extends Money> getShapeSchedule();

	/*********************** Build Methods  ***********************/
	ShapingProvision build();
	
	ShapingProvision.ShapingProvisionBuilder toBuilder();
	
	static ShapingProvision.ShapingProvisionBuilder builder() {
		return new ShapingProvision.ShapingProvisionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ShapingProvision> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ShapingProvision> getType() {
		return ShapingProvision.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("shapeSchedule"), processor, Money.class, getShapeSchedule());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ShapingProvisionBuilder extends ShapingProvision, RosettaModelObjectBuilder {
		Money.MoneyBuilder getOrCreateShapeSchedule(int _index);
		List<? extends Money.MoneyBuilder> getShapeSchedule();
		ShapingProvision.ShapingProvisionBuilder addShapeSchedule(Money shapeSchedule0);
		ShapingProvision.ShapingProvisionBuilder addShapeSchedule(Money shapeSchedule1, int _idx);
		ShapingProvision.ShapingProvisionBuilder addShapeSchedule(List<? extends Money> shapeSchedule2);
		ShapingProvision.ShapingProvisionBuilder setShapeSchedule(List<? extends Money> shapeSchedule3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("shapeSchedule"), processor, Money.MoneyBuilder.class, getShapeSchedule());
		}
		

		ShapingProvision.ShapingProvisionBuilder prune();
	}

	/*********************** Immutable Implementation of ShapingProvision  ***********************/
	class ShapingProvisionImpl implements ShapingProvision {
		private final List<? extends Money> shapeSchedule;
		
		protected ShapingProvisionImpl(ShapingProvision.ShapingProvisionBuilder builder) {
			this.shapeSchedule = ofNullable(builder.getShapeSchedule()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("shapeSchedule")
		public List<? extends Money> getShapeSchedule() {
			return shapeSchedule;
		}
		
		@Override
		public ShapingProvision build() {
			return this;
		}
		
		@Override
		public ShapingProvision.ShapingProvisionBuilder toBuilder() {
			ShapingProvision.ShapingProvisionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ShapingProvision.ShapingProvisionBuilder builder) {
			ofNullable(getShapeSchedule()).ifPresent(builder::setShapeSchedule);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ShapingProvision _that = getType().cast(o);
		
			if (!ListEquals.listEquals(shapeSchedule, _that.getShapeSchedule())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (shapeSchedule != null ? shapeSchedule.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ShapingProvision {" +
				"shapeSchedule=" + this.shapeSchedule +
			'}';
		}
	}

	/*********************** Builder Implementation of ShapingProvision  ***********************/
	class ShapingProvisionBuilderImpl implements ShapingProvision.ShapingProvisionBuilder {
	
		protected List<Money.MoneyBuilder> shapeSchedule = new ArrayList<>();
	
		public ShapingProvisionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("shapeSchedule")
		public List<? extends Money.MoneyBuilder> getShapeSchedule() {
			return shapeSchedule;
		}
		
		public Money.MoneyBuilder getOrCreateShapeSchedule(int _index) {
		
			if (shapeSchedule==null) {
				this.shapeSchedule = new ArrayList<>();
			}
			Money.MoneyBuilder result;
			return getIndex(shapeSchedule, _index, () -> {
						Money.MoneyBuilder newShapeSchedule = Money.builder();
						return newShapeSchedule;
					});
		}
		
	
		@Override
		public ShapingProvision.ShapingProvisionBuilder addShapeSchedule(Money shapeSchedule) {
			if (shapeSchedule!=null) this.shapeSchedule.add(shapeSchedule.toBuilder());
			return this;
		}
		
		@Override
		public ShapingProvision.ShapingProvisionBuilder addShapeSchedule(Money shapeSchedule, int _idx) {
			getIndex(this.shapeSchedule, _idx, () -> shapeSchedule.toBuilder());
			return this;
		}
		@Override 
		public ShapingProvision.ShapingProvisionBuilder addShapeSchedule(List<? extends Money> shapeSchedules) {
			if (shapeSchedules != null) {
				for (Money toAdd : shapeSchedules) {
					this.shapeSchedule.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("shapeSchedule")
		public ShapingProvision.ShapingProvisionBuilder setShapeSchedule(List<? extends Money> shapeSchedules) {
			if (shapeSchedules == null)  {
				this.shapeSchedule = new ArrayList<>();
			}
			else {
				this.shapeSchedule = shapeSchedules.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public ShapingProvision build() {
			return new ShapingProvision.ShapingProvisionImpl(this);
		}
		
		@Override
		public ShapingProvision.ShapingProvisionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ShapingProvision.ShapingProvisionBuilder prune() {
			shapeSchedule = shapeSchedule.stream().filter(b->b!=null).<Money.MoneyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getShapeSchedule()!=null && getShapeSchedule().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ShapingProvision.ShapingProvisionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ShapingProvision.ShapingProvisionBuilder o = (ShapingProvision.ShapingProvisionBuilder) other;
			
			merger.mergeRosetta(getShapeSchedule(), o.getShapeSchedule(), this::getOrCreateShapeSchedule);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ShapingProvision _that = getType().cast(o);
		
			if (!ListEquals.listEquals(shapeSchedule, _that.getShapeSchedule())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (shapeSchedule != null ? shapeSchedule.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ShapingProvisionBuilder {" +
				"shapeSchedule=" + this.shapeSchedule +
			'}';
		}
	}
}
