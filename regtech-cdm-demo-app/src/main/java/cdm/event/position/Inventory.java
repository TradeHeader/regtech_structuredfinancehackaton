package cdm.event.position;

import cdm.event.position.Inventory;
import cdm.event.position.Inventory.InventoryBuilder;
import cdm.event.position.Inventory.InventoryBuilderImpl;
import cdm.event.position.Inventory.InventoryImpl;
import cdm.event.position.InventoryRecord;
import cdm.event.position.meta.InventoryMeta;
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
 * A data type that can be used to describe an inventory of securities.
 * @version ${project.version}
 */
@RosettaDataType(value="Inventory", builder=Inventory.InventoryBuilderImpl.class, version="${project.version}")
public interface Inventory extends RosettaModelObject {

	InventoryMeta metaData = new InventoryMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An array holding the list of inventory being described. Each element in the inventoryRecord array represents an individual piece of inventory i.e. a security.
	 */
	List<? extends InventoryRecord> getInventoryRecord();

	/*********************** Build Methods  ***********************/
	Inventory build();
	
	Inventory.InventoryBuilder toBuilder();
	
	static Inventory.InventoryBuilder builder() {
		return new Inventory.InventoryBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Inventory> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Inventory> getType() {
		return Inventory.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("inventoryRecord"), processor, InventoryRecord.class, getInventoryRecord());
	}
	

	/*********************** Builder Interface  ***********************/
	interface InventoryBuilder extends Inventory, RosettaModelObjectBuilder {
		InventoryRecord.InventoryRecordBuilder getOrCreateInventoryRecord(int _index);
		List<? extends InventoryRecord.InventoryRecordBuilder> getInventoryRecord();
		Inventory.InventoryBuilder addInventoryRecord(InventoryRecord inventoryRecord0);
		Inventory.InventoryBuilder addInventoryRecord(InventoryRecord inventoryRecord1, int _idx);
		Inventory.InventoryBuilder addInventoryRecord(List<? extends InventoryRecord> inventoryRecord2);
		Inventory.InventoryBuilder setInventoryRecord(List<? extends InventoryRecord> inventoryRecord3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("inventoryRecord"), processor, InventoryRecord.InventoryRecordBuilder.class, getInventoryRecord());
		}
		

		Inventory.InventoryBuilder prune();
	}

	/*********************** Immutable Implementation of Inventory  ***********************/
	class InventoryImpl implements Inventory {
		private final List<? extends InventoryRecord> inventoryRecord;
		
		protected InventoryImpl(Inventory.InventoryBuilder builder) {
			this.inventoryRecord = ofNullable(builder.getInventoryRecord()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("inventoryRecord")
		public List<? extends InventoryRecord> getInventoryRecord() {
			return inventoryRecord;
		}
		
		@Override
		public Inventory build() {
			return this;
		}
		
		@Override
		public Inventory.InventoryBuilder toBuilder() {
			Inventory.InventoryBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Inventory.InventoryBuilder builder) {
			ofNullable(getInventoryRecord()).ifPresent(builder::setInventoryRecord);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Inventory _that = getType().cast(o);
		
			if (!ListEquals.listEquals(inventoryRecord, _that.getInventoryRecord())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (inventoryRecord != null ? inventoryRecord.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Inventory {" +
				"inventoryRecord=" + this.inventoryRecord +
			'}';
		}
	}

	/*********************** Builder Implementation of Inventory  ***********************/
	class InventoryBuilderImpl implements Inventory.InventoryBuilder {
	
		protected List<InventoryRecord.InventoryRecordBuilder> inventoryRecord = new ArrayList<>();
	
		public InventoryBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("inventoryRecord")
		public List<? extends InventoryRecord.InventoryRecordBuilder> getInventoryRecord() {
			return inventoryRecord;
		}
		
		public InventoryRecord.InventoryRecordBuilder getOrCreateInventoryRecord(int _index) {
		
			if (inventoryRecord==null) {
				this.inventoryRecord = new ArrayList<>();
			}
			InventoryRecord.InventoryRecordBuilder result;
			return getIndex(inventoryRecord, _index, () -> {
						InventoryRecord.InventoryRecordBuilder newInventoryRecord = InventoryRecord.builder();
						return newInventoryRecord;
					});
		}
		
	
		@Override
		public Inventory.InventoryBuilder addInventoryRecord(InventoryRecord inventoryRecord) {
			if (inventoryRecord!=null) this.inventoryRecord.add(inventoryRecord.toBuilder());
			return this;
		}
		
		@Override
		public Inventory.InventoryBuilder addInventoryRecord(InventoryRecord inventoryRecord, int _idx) {
			getIndex(this.inventoryRecord, _idx, () -> inventoryRecord.toBuilder());
			return this;
		}
		@Override 
		public Inventory.InventoryBuilder addInventoryRecord(List<? extends InventoryRecord> inventoryRecords) {
			if (inventoryRecords != null) {
				for (InventoryRecord toAdd : inventoryRecords) {
					this.inventoryRecord.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("inventoryRecord")
		public Inventory.InventoryBuilder setInventoryRecord(List<? extends InventoryRecord> inventoryRecords) {
			if (inventoryRecords == null)  {
				this.inventoryRecord = new ArrayList<>();
			}
			else {
				this.inventoryRecord = inventoryRecords.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public Inventory build() {
			return new Inventory.InventoryImpl(this);
		}
		
		@Override
		public Inventory.InventoryBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Inventory.InventoryBuilder prune() {
			inventoryRecord = inventoryRecord.stream().filter(b->b!=null).<InventoryRecord.InventoryRecordBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInventoryRecord()!=null && getInventoryRecord().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Inventory.InventoryBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Inventory.InventoryBuilder o = (Inventory.InventoryBuilder) other;
			
			merger.mergeRosetta(getInventoryRecord(), o.getInventoryRecord(), this::getOrCreateInventoryRecord);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Inventory _that = getType().cast(o);
		
			if (!ListEquals.listEquals(inventoryRecord, _that.getInventoryRecord())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (inventoryRecord != null ? inventoryRecord.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InventoryBuilder {" +
				"inventoryRecord=" + this.inventoryRecord +
			'}';
		}
	}
}
